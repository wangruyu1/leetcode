package com.canal.demo.handler.feature;

import com.canal.demo.handler.feature.model.RowEvent;

import java.util.Map;

public interface FeatureHandler {
    /**
     * 处理column变更
     *
     * @param eventType     事件类型
     * @param databaseName  数据库名
     * @param tableName     表名
     * @param col           列名
     * @param beforeColumns 变更前数据
     * @param afterColumns  变更后数据
     */
    void handler(RowEvent.EventType eventType, String databaseName, String tableName, String col, Map<String, Object> beforeColumns, Map<String, Object> afterColumns);

    /**
     * 查询feature
     *
     * @param ids
     * @return
     */
    Object getFeature(Object... ids);

}
