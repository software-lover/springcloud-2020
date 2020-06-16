package com.wzm.springcloud;

import com.wzm.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 21:34
 */
@SpringBootApplication
@EnableEurekaClient
/**
 * 表示对于 CLOUD-PAYMENT-SERVICE 服务来说，使用自定义的负载均衡策略
 */
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
