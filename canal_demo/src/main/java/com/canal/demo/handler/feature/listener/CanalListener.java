package com.canal.demo.handler.feature.listener;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.canal.demo.connector.ConnectorManager;
import com.canal.demo.connector.ConnectorProperty;
import com.canal.demo.exception.ParseEventException;
import com.canal.demo.handler.feature.RowDataManager;
import com.canal.demo.handler.feature.model.RowEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class CanalListener implements Listener {

    public static void main(String[] aargs) {

    }

    @Override
    public void startListen() {
        log.info("开始监听canal binlog");
        CanalConnector exempalConnector = null;

        try {
            exempalConnector = ConnectorManager.getConnector(ConnectorManager.CONNECTOR_EXAMPLE_NAME);
            exempalConnector.connect();
            //TODO:建议服务端配置
//            exempalConnector.subscribe(".*\\..*");
            exempalConnector.subscribe("boss\\.xdual.*");
            while (true) {
                Message message = exempalConnector.getWithoutAck(ConnectorProperty.BATCH_SIZE); // 获取指定数量的数据
                long batchId = message.getId();
                List<CanalEntry.Entry> entries = Optional.ofNullable(message.getEntries()).orElse(Collections.emptyList());
                if (batchId == -1 || entries.size() == 0) {
                    log.info("本次未查到变更数据");
                    if (batchId != -1) {
                        exempalConnector.ack(batchId); // 提交确认
                        // connector.rollback(batchId); // 处理失败, 回滚数据
                    }
                    Thread.sleep(1000);
                } else {
//                    entries = entries.stream().filter(e -> e.getHeader().getTableName().startsWith("xdual")).collect(Collectors.toList());
                    for (CanalEntry.Entry entry : entries) {
                        if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                            continue;
                        }
                        if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
                            RowChange rowChage = null;
                            try {
                                rowChage = RowChange.parseFrom(entry.getStoreValue());
                            } catch (Exception e) {
                                throw new ParseEventException("解析事件数据发生错误:" + entry.toString(), e);
                            }
                            CanalEntry.EventType eventType = rowChage.getEventType();
                            //过滤掉DDL语句
                            if (rowChage.getEventType() == EventType.QUERY || rowChage.getIsDdl()) {
                                continue;
                            }
                            for (RowData rowData : rowChage.getRowDatasList()) {
                                Map<String, Object> beforeColumnMap = parseColumn(rowData.getBeforeColumnsList());
                                Map<String, Object> afterColumnMap = parseColumn(rowData.getAfterColumnsList());
                                if (log.isDebugEnabled()) {
                                    log.debug("变更前:{},变更后:{}", beforeColumnMap, afterColumnMap);
                                }
                                RowEvent rowEvent = RowEvent.builder()
                                        .bofore(beforeColumnMap)
                                        .after(afterColumnMap)
                                        .databaseName(entry.getHeader().getSchemaName())
                                        .tableName(entry.getHeader().getTableName())
                                        .build();
                                if (eventType == CanalEntry.EventType.DELETE) {
                                    rowEvent.setEventType(RowEvent.EventType.DELETE);
                                } else if (eventType == CanalEntry.EventType.INSERT) {
                                    rowEvent.setEventType(RowEvent.EventType.INSERT);
                                } else {
                                    rowEvent.setEventType(RowEvent.EventType.UPDATE);
                                }
                                RowDataManager.offer(rowEvent);
                            }
                        }
                    }
                    if (batchId != -1) {
                        exempalConnector.ack(batchId); // 提交确认
                        // connector.rollback(batchId); // 处理失败, 回滚数据
                    }
                }
            }
        } catch (Exception e) {
            log.error("处理binlog发生异常.", e);
        } finally {
            exempalConnector.disconnect();
        }

        log.info("结束监听canal binlog");
    }

    protected Map<String, Object> parseColumn(List<Column> columns) {
        if (columns == null || columns.size() < 1) {
            return Collections.emptyMap();
        }
        Map<String, Object> columnMap = new HashMap<>(columns.size());
        for (CanalEntry.Column column : columns) {
            StringBuilder builder = new StringBuilder();
            if (StringUtils.containsIgnoreCase(column.getMysqlType(), "BLOB")
                    || StringUtils.containsIgnoreCase(column.getMysqlType(), "BINARY")) {
//                    String columnName = column.getName();
//                    byte[] bytes = column.getValue().getBytes("ISO-8859-1");
//                    columnMap.put(columnName, bytes);
            } else {
                columnMap.put(column.getName(), column.getValue());
            }
        }
        return columnMap;
    }

}
