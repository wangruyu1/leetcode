package com.canal.demo.handler;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.Map;

public interface Handler {
    /**
     * 删除处理
     *
     * @param beforeColumns
     */
    void handerlDelete(Map<String, Object> beforeColumns);

    /**
     * 插入数据处理
     *
     * @param afterColumns
     */
    void handlerInsert(Map<String, Object> afterColumns);

    /**
     * 更新数据处理
     *
     * @param beforeColumns 更新之前的列
     * @param afterColumns  更新之后的列
     */
    void handerUpdate(Map<String, Object> beforeColumns, Map<String, Object> afterColumns);

}
