package com.wzm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description 启动类
 * @author json
 * @date 2020-05-25 22:28
 */

@SpringBootApplication
@EnableEurekaClient
/**
 * 服务发现，添加这个注解后，Controller里面的discovery方法就可以访问，返回服务的注册信息
 */
@EnableDiscoveryClient
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
