package com.lizhen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * pc-web项目的启动入口
 * Created by lizhen on 2018/4/21.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PCWebApp {
    public static void main(String[] args) {
        SpringApplication.run(PCWebApp.class, args);
    }
}
