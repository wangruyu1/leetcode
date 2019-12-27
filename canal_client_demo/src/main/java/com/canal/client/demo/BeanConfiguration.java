package com.canal.client.demo;

import com.canal.client.demo.constant.CanalConfigConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClusterCanalClientTest clusterCanalClientTest() {
        return new ClusterCanalClientTest(CanalConfigConstant.DESTINATION);
    }
}
