package com.canal.demo.handler.feature.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface TableXdual {

    String databaseName = "boss";
    String tableName = "xdual";
    String colId = "ID";
    String colX = "X";
}
