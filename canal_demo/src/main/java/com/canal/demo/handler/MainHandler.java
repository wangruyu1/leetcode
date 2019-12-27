package com.canal.demo.handler;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.canal.demo.connector.ConnectorManager;
import com.canal.demo.connector.ConnectorProperty;
import com.canal.demo.exception.ParseEventException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
//@Component
public class MainHandler implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        int exitTimes = 0;
        while (exitTimes <= 5) {
            startListen();
            exitTimes++;
        }
    }

    public void startListen() {
        log.info("开始监听数据库");
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
                    entries = entries.stream().filter(e -> e.getHeader().getTableName().startsWith("xdual")).collect(Collectors.toList());
                    for (Entry entry : entries) {
                        if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                            continue;
                        }
//                        if (!entry.getHeader().getTableName().startsWith("xdual")) {
//                            continue;
//                        }
                        if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
                            RowChange rowChage = null;
                            try {
                                rowChage = RowChange.parseFrom(entry.getStoreValue());
                            } catch (Exception e) {
                                throw new ParseEventException("解析事件数据发生错误:" + entry.toString(), e);
                            }
                            EventType eventType = rowChage.getEventType();
                            //过滤掉DDL语句
                            if (rowChage.getEventType() == EventType.QUERY || rowChage.getIsDdl()) {
                                continue;
                            }

                            log.info("binlog file:{},offset:{},schema:{},表:{}",
                                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName());
                            log.info("sql---->{}", rowChage.getSql());
                            //获取数据库和表名
                            String handlerName = entry.getHeader().getSchemaName() + "." + entry.getHeader().getTableName();
                            Handler handler = AbstractHandler.handlers.get(handlerName);
                            if (handler == null) {
                                log.warn("找不到{}对应的handler", handlerName);
                                continue;
                            }
                            for (RowData rowData : rowChage.getRowDatasList()) {
                                Map<String, Object> beforeColumnMap = parseColumn(rowData.getBeforeColumnsList());
                                Map<String, Object> afterColumnMap = parseColumn(rowData.getAfterColumnsList());
                                if (log.isDebugEnabled()) {
                                    log.debug("变更前:{},变更后:{}", beforeColumnMap, afterColumnMap);
                                }
                                if (eventType == EventType.DELETE) {
                                    handler.handerlDelete(beforeColumnMap);
                                } else if (eventType == CanalEntry.EventType.INSERT) {
                                    handler.handlerInsert(afterColumnMap);
                                } else {
                                    handler.handerUpdate(beforeColumnMap, afterColumnMap);
                                }
                            }
                        }
                    }
                    if (batchId != -1) {
                        exempalConnector.ack(batchId); // 提交确认
                        // connector.rollback(batchId); // 处理失败, 回滚数据
                    }
                }
            }
        } catch (CanalClientException | ParseEventException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (exempalConnector != null) {
                exempalConnector.disconnect();
            }
        }
        log.info("结束监听数据库");
    }

    /**
     * 解析每列数据
     *
     * @param columns
     */
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
