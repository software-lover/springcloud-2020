package com.wzm.alibabacloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-14 16:06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacosMain83 {

    public static void main(String[] args) {

        SpringApplication.run(OrderNacosMain83.class, args);
    }
}
