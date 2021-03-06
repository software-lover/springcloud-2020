package com.wzm.alibabacloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-21 16:03
 */

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9004 {

    public static void main(String[] args) {

        SpringApplication.run(PaymentMain9004.class, args);
    }
}
