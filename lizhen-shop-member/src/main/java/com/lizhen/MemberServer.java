package com.lizhen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * member项目启动入口
 * Created by lizhen on 2018/4/20 0020.
 */
@SpringBootApplication
@EnableEurekaClient
public class MemberServer {
    public static void main(String[] args) {
        SpringApplication.run(MemberServer.class,args);
    }
}
