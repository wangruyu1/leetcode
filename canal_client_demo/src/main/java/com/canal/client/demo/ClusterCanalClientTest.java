package com.canal.client.demo;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.canal.client.demo.constant.CanalConfigConstant;
import org.springframework.stereotype.Component;

/**
 * 集群模式的测试例子
 *
 * @author jianghang 2013-4-15 下午04:19:20
 * @version 1.0.4
 */
public class ClusterCanalClientTest extends AbstractCanalClientTest {

    public ClusterCanalClientTest(String destination) {
        super(destination);
    }

    public static void main(String args[]) {
        ClusterCanalClientTest test = new ClusterCanalClientTest(CanalConfigConstant.DESTINATION);
        test.startClient();
    }

    public void startClient() {
        String destination = CanalConfigConstant.DESTINATION;

        // 基于固定canal server的地址，建立链接，其中一台server发生crash，可以支持failover
        // CanalConnector connector = CanalConnectors.newClusterConnector(
        // Arrays.asList(new InetSocketAddress(
        // AddressUtils.getHostIp(),
        // 11111)),
        // "stability_test", "", "");

        // 基于zookeeper动态获取canal server的地址，建立链接，其中一台server发生crash，可以支持failover
        CanalConnector connector = CanalConnectors.newClusterConnector("192.168.2.137:2181,192.168.2.136:2181", destination, "canal", "canal");

        final ClusterCanalClientTest clientTest = new ClusterCanalClientTest(destination);
        clientTest.setConnector(connector);
        clientTest.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {
                try {
                    logger.info("## stop the canal client");
                    clientTest.stop();
                } catch (Throwable e) {
                    logger.warn("##something goes wrong when stopping canal:", e);
                } finally {
                    logger.info("## canal client is down.");
                }
            }

        });
    }
}
