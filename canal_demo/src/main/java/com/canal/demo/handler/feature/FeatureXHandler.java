package com.canal.demo.handler.feature;

import com.canal.demo.constant.RedisKeyConstant;
import com.canal.demo.handler.feature.model.RowEvent;
import com.canal.demo.handler.feature.model.TableXdual;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class FeatureXHandler extends AbstractFeatureHandler {

    private static final String HASH_FIELD_NAME = "x";

    @Override
    public void handler(RowEvent.EventType eventType, String databaseName, String tableName, String col, Map<String, Object> beforeColumns, Map<String, Object> afterColumns) {
        if (Objects.equals(TableXdual.databaseName, databaseName) && Objects.equals(TableXdual.tableName, tableName)
                && Objects.equals(TableXdual.colX, col)) {
            if (eventType == RowEvent.EventType.UPDATE) {
                Object bossId = afterColumns.get(TableXdual.colId);
                String hashKey = String.format(RedisKeyConstant.BOSS_STATIC_FEATURE, bossId);
                //增加
                stringRedisTemplate.opsForHash().increment(hashKey, HASH_FIELD_NAME, 1L);
            } else if (eventType == RowEvent.EventType.DELETE) {

            } else if (eventType == RowEvent.EventType.UPDATE) {

            }
        }
    }

    @Override
    public Object getFeature(Object... ids) {
        String hashKey = String.format(RedisKeyConstant.BOSS_STATIC_FEATURE, ids[0]);
        return stringRedisTemplate.opsForHash().get(hashKey, HASH_FIELD_NAME);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //建立和表column关联关系
        synchronized (AbstractFeatureHandler.featureHandlers) {
            String key = TableXdual.databaseName + "." + TableXdual.tableName + "." + TableXdual.colX;
            List<FeatureHandler> handlers = AbstractFeatureHandler.featureHandlers.get(key);
            if (handlers == null) {
                handlers = new ArrayList<>(2);
                AbstractFeatureHandler.featureHandlers.put(key, handlers);
            }
            handlers.add(this);
        }
    }
}
