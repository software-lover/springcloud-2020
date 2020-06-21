package com.wzm.alibabacloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-21 10:50
 */

@SpringBootApplication
@EnableDiscoveryClient
public class MainApp8401 {

    public static void main(String[] args) {

        SpringApplication.run(MainApp8401.class, args);
    }
}
