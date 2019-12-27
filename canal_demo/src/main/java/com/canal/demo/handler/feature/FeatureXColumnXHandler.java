package com.canal.demo.handler.feature;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class FeatureXColumnXHandler extends AbstractFeatureColumnHandler {


    @Override
    public void handlerDelete(Map<String, Object> beforeMap) {

    }

    @Override
    public void handlerInsert(Map<String, Object> afterMap) {

    }

    @Override
    public void handlerUpdate(Map<String, Object> beforeMap, Map<String, Object> afterMap) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.databaseName = "";
    }
}
