package com.wzm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-07 12:55
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigCenterMain3344 {

    public static void main(String[] args) {

        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
