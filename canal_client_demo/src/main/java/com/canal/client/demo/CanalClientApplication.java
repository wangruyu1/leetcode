package com.canal.client.demo;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class CanalClientApplication implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        SpringApplication.run(CanalClientApplication.class, args);
        if (args != null && args.length > 0) {
            int type = Integer.valueOf(args[0]);
            if (type == 1) {
                SimpleCanalClientExample example = applicationContext.getBean(SimpleCanalClientExample.class);
                example.listen();
            } else if (type == 2) {
                ClusterCanalClientTest clusterCanalClientTest = applicationContext.getBean(ClusterCanalClientTest.class);
                clusterCanalClientTest.startClient();
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CanalClientApplication.applicationContext = applicationContext;
    }
}
