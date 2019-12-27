package com.canal.demo.handler.feature.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RowEvent {

    private String databaseName;
    private String tableName;
    private EventType eventType;
    private Map<String, Object> bofore;
    private Map<String, Object> after;

    public enum EventType {
        DELETE, UPDATE, INSERT, QUERY;
    }
}


