package com.wangyaochong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderMain8000Feign {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8000Feign.class, args);
    }
}
