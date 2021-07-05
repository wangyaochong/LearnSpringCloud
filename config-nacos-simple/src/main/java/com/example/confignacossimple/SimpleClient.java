package com.example.confignacossimple;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executor;

public class SimpleClient {
    public static void main(String[] args) throws NacosException {
        String serverAddr = "127.0.0.1:8848";
        String dataId = "nacos-simple-demo.yml";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", "dev");
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {
            @Override public Executor getExecutor() {
                return null;
            }

            @Override public void receiveConfigInfo(String s) {
                System.out.println(s);
            }
        });
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }
}
