package com.wangyaochong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderMain8000zk {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8000zk.class, args);
    }
}
