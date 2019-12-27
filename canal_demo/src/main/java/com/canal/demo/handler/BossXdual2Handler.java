package com.canal.demo.handler;

import com.canal.demo.constant.RedisKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BossXdual2Handler extends AbstractHandler {

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化数据库名
        this.databaseName = "boss";
        //初始化表名
        this.tableName = "xdual2";
        id = "id";
        //添加字段到hash_key field 操作的映射
        this.columnHashKeyMap.put("create_time", RedisKeyConstant.BOSS_STATIC_FEATURE + HASH_KEY_FILED_SEP + "createTime" + HASH_KEY_FILED_SEP + OP_HASH_PUT);
        super.afterPropertiesSet();
    }
}
