package com.lizhen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 消息服务的启动入口
 * Created by lizhen on 2018/4/21.
 */
@SpringBootApplication
@EnableEurekaClient
public class MessageServer {
    public static void main(String[] args) {
        SpringApplication.run(MessageServer.class, args);
    }
}
