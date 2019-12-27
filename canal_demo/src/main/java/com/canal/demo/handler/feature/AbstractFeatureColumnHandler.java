package com.canal.demo.handler.feature;

import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractFeatureColumnHandler implements FeatureColumnHandler, InitializingBean {

    protected String databaseName;
    protected String tableName;
    protected String columnName;

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
