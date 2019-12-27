package com.canal.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
public abstract class AbstractHandler implements Handler, InitializingBean {

    //数据库名
    protected String databaseName = "";
    // 表名
    protected String tableName = "";
    //hash的key和field的分隔符
    protected static final String HASH_KEY_FILED_SEP = "\001";
    //hash的increment操作
    protected static final String OP_HASH_INC = "inc";
    //hash的put操作
    protected static final String OP_HASH_PUT = "put";
    protected String id = "id";
    //列名到hashkey的映射
    protected Map<String, String> columnHashKeyMap = new HashMap<>(8);
    // 保存tableName-handler
    public static final Map<String, Handler> handlers = new HashMap<>(8);

    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void handerlDelete(Map<String, Object> beforeColumns) {
        Object id = this.getId(beforeColumns);
        this.preHandler(beforeColumns, Collections.emptyMap());
    }

    @Override
    public void handlerInsert(Map<String, Object> afterColumns) {
        Object id = this.getId(afterColumns);
        this.preHandler(Collections.emptyMap(), afterColumns);
        updateHashValue(afterColumns, id);
    }

    /**
     * 获取id
     *
     * @param afterColumns
     * @return
     */
    protected Object getId(Map<String, Object> afterColumns) {
        if (afterColumns == null) {
            return null;
        }
        return afterColumns.get(id);
    }

    /**
     * 更新变更的hash值
     *
     * @param beforeColumns 更新之前的列
     * @param afterColumns  更新之后的列
     */
    @Override
    public void handerUpdate(Map<String, Object> beforeColumns, Map<String, Object> afterColumns) {
        Object id = this.getId(afterColumns);
        this.preHandler(beforeColumns, afterColumns);
        //更新hash的值
        updateHashValue(afterColumns, id);
    }

    /**
     * 更新hash的field
     *
     * @param afterColumns
     */
    private void updateHashValue(Map<String, Object> afterColumns, Object id) {
        if (!CollectionUtils.isEmpty(afterColumns)) {
            for (Map.Entry<String, Object> entry : afterColumns.entrySet()) {
                String hashKeyField = columnHashKeyMap.get(entry.getKey());
                if (StringUtils.isEmpty(hashKeyField)) {
                    log.warn("没有配置{}.{}.{}对应的redis的hash映射", this.databaseName, this.tableName, entry.getKey());
                    continue;
                }
                String[] keyFieldArray = hashKeyField.split(HASH_KEY_FILED_SEP);
                //hash中的key的id

                String hashKey = String.format(keyFieldArray[0], id);
                String field = keyFieldArray[1];
                String op = keyFieldArray[2];
                Object newValue = entry.getValue();
                if (keyFieldArray.length == 2 || Objects.equals(op, OP_HASH_PUT)) {
                    //更新field对应的值
                    stringRedisTemplate.opsForHash().put(hashKey, field, newValue);
                } else if (Objects.equals(keyFieldArray[2], OP_HASH_INC)) {
                    stringRedisTemplate.opsForHash().increment(hashKey, field, 1L);
                }
                //TODO:
//                stringRedisTemplate.opsForHash().putAll();
            }
        }
    }


    /**
     * 预处理,默认是得到变更的列
     *
     * @param beforeColumns
     * @param afterColumns
     */
    protected void preHandler(Map<String, Object> beforeColumns, Map<String, Object> afterColumns) {
        //得到变更的列
        if (CollectionUtils.isEmpty(beforeColumns) || CollectionUtils.isEmpty(afterColumns)) {
            return;
        }
        Set<String> notUpdatedColumns = new HashSet<>(beforeColumns.size() / 2);
        beforeColumns.entrySet().forEach(entry -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object afterValue = afterColumns.get(key);
            if (Objects.equals(value, afterValue)) {
                notUpdatedColumns.add(key);
            }
        });
        if (!CollectionUtils.isEmpty(notUpdatedColumns)) {
            for (String column : notUpdatedColumns) {
                beforeColumns.remove(column);
                afterColumns.remove(column);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //添加表到handler的映射
        handlers.put(databaseName + "." + tableName, this);
        log.info("注入{}.{}处理器:{}", databaseName, tableName, this.getClass().getName());
    }

}
