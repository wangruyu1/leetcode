package com.canal.demo.handler.feature;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;

@Slf4j
public abstract class AbstractFeatureHandler implements FeatureHandler, InitializingBean {

    // 保存tableName-col->handler
    public static final Map<String, List<FeatureHandler>> featureHandlers = new HashMap<>(8);
    private List<FeatureColumnHandler> featureColumnHandlers = new ArrayList<>(2);

    @Autowired(required = false)
    protected StringRedisTemplate stringRedisTemplate;

}
