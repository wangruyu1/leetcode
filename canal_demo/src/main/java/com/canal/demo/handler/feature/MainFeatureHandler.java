package com.canal.demo.handler.feature;

import com.canal.demo.handler.feature.listener.CanalListener;
import com.canal.demo.handler.feature.listener.Listener;
import com.canal.demo.handler.feature.model.RowEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MainFeatureHandler implements CommandLineRunner {

    @Resource(type = CanalListener.class)
    private Listener listener;

    @Override
    public void run(String... args) throws Exception {
        //启动canal消费
        new Thread(() -> {
            listener.startListen();
        }).start();

        while (true) {
            try {
                //消费事件
                RowEvent rowEvent = RowDataManager.poll(2, TimeUnit.SECONDS);
                if (rowEvent == null) {
                    log.info("消费队列没数据");
                    continue;
                }
                //处理列事件
                Map<String, Object> beforeMap = rowEvent.getBofore();
                Map<String, Object> afterMap = rowEvent.getAfter();
                String databaseName = rowEvent.getDatabaseName();
                String tableName = rowEvent.getTableName();
                if (!tableName.startsWith("xdual")) {
                    continue;
                }
                //得到有变更的列
                Set<String> diffColumns = Optional.ofNullable(getColumnForDifferentValue(beforeMap, afterMap)).orElse(Collections.emptySet());
                //处理变更的列
                for (String column : diffColumns) {
                    List<FeatureHandler> featureHandlers = AbstractFeatureHandler.featureHandlers.get(databaseName + "." + tableName + "." + column);
                    if (featureHandlers == null) {
                        log.info("{}.{}.{}未设置handler", rowEvent.getDatabaseName(), rowEvent.getTableName(), column);
                        continue;
                    }
                    //有变更并且设置了handler
                    for (FeatureHandler handler : featureHandlers) {
                        handler.handler(rowEvent.getEventType(), databaseName, tableName, column, beforeMap, afterMap);
                    }
                }
            } catch (InterruptedException e) {
                log.warn("没有可消费的数据");
            }
        }

    }

    /**
     * 得到不同值得列
     *
     * @param beforeColumns
     * @param afterColumns
     */
    protected Set<String> getColumnForDifferentValue(Map<String, Object> beforeColumns, Map<String, Object> afterColumns) {
        //得到变更的列
        if (CollectionUtils.isEmpty(beforeColumns) || CollectionUtils.isEmpty(afterColumns)) {
            return Collections.emptySet();
        }
        Set<String> diffValueColumns = new HashSet<>(beforeColumns.size() / 2);
        beforeColumns.entrySet().forEach(entry -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object afterValue = afterColumns.get(key);
            if (!Objects.equals(value, afterValue)) {
                diffValueColumns.add(key);
            }
        });
        return diffValueColumns;
    }

}
