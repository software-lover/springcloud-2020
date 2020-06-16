package com.wzm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-30 18:16
 */
@SpringBootApplication
@EnableDiscoveryClient
/**
 * 对于用eureka作为注册中心，注册上去的服务假如停掉后，eureka会进入自我保护机制，不会立马就删除掉服务实例信息
 * 对于用consul作为注册中心，注册上去的服务假如停掉，那么到了心跳时间后，该服务实例信息会被立马删除掉
 */
public class PaymentMain8006 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8006.class, args);
    }
}
