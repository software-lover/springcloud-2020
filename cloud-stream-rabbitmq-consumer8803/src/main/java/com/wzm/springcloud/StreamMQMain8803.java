package com.wzm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-13 12:57
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMQMain8803 {

    public static void main(String[] args) {

        SpringApplication.run(StreamMQMain8803.class, args);
    }
}
