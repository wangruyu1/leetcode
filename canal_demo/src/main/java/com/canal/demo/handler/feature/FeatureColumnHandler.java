package com.canal.demo.handler.feature;

import java.util.Map;

public interface FeatureColumnHandler {
    /**
     * 处理删除操作
     *
     * @param beforeMap
     */
    public void handlerDelete(Map<String, Object> beforeMap);

    /**
     * 处理新增操作
     *
     * @param afterMap
     */
    public void handlerInsert(Map<String, Object> afterMap);

    /**
     * 处理更新操作
     *
     * @param beforeMap
     * @param afterMap
     */
    public void handlerUpdate(Map<String, Object> beforeMap, Map<String, Object> afterMap);
}
