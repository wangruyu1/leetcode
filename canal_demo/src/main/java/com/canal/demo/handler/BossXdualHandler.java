package com.canal.demo.handler;

import com.alibaba.fastjson.JSON;
import com.canal.demo.constant.RedisKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Slf4j
@Component
public class BossXdualHandler extends AbstractHandler {

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化数据库名
        this.databaseName = "boss";
        //初始化表名
        this.tableName = "xdual";
        id = "ID";
        //添加字段到hash_key field 操作的映射
        this.columnHashKeyMap.put("X", RedisKeyConstant.BOSS_STATIC_FEATURE + HASH_KEY_FILED_SEP + "x" + HASH_KEY_FILED_SEP + OP_HASH_INC);
        super.afterPropertiesSet();
    }
}
