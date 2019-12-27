package com.canal.demo.connector;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ConnectorManager {

    private static final String ZK_ADDRESS = "192.168.2.137:2181,192.168.2.136:2181";
    private static final String DEFAULT_NAME = "canal";
    private static final String DEFAULT_PASSWORD = "canal";
    public static final String CONNECTOR_EXAMPLE_NAME = "example";
    private static CanalConnector CONNECTOR_EXAMPLE = null;

    public ConnectorManager() {
        initConnector(CONNECTOR_EXAMPLE_NAME);
    }

    private void initConnector(String destination) {
        try {
            CONNECTOR_EXAMPLE = CanalConnectors.newClusterConnector(ZK_ADDRESS, destination, DEFAULT_NAME, DEFAULT_PASSWORD);
            log.info("初始化实例{}连接成功:zk:{},用户名:{},密码:{}", destination, ZK_ADDRESS, DEFAULT_NAME, DEFAULT_PASSWORD);
        } catch (Exception e) {
            log.error("创建实例{}连接发生错误:zk:{},用户名:,密码:{}", destination, ZK_ADDRESS, DEFAULT_NAME, DEFAULT_PASSWORD);
            log.error("", e);
        }
    }

    /**
     * 查询队列实例
     *
     * @param destination
     * @return null=实例不存在
     */
    public static CanalConnector getConnector(String destination) {
        if (StringUtils.isEmpty(destination)) {
            log.warn("实例{}名称错误", destination);
            return null;
        }
        switch (destination) {
            case CONNECTOR_EXAMPLE_NAME:
                return CONNECTOR_EXAMPLE;
            default:
                return null;
        }
    }
}
