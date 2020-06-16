package com.wzm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description 启动类
 * @author json
 * @date 2020-05-25 22:28
 */

@SpringBootApplication
/**
 * 该注解用于向使用consul或者zookeeper作为注册中心注册服务
 *
 * 对于用eureka作为注册中心，注册上去的服务假如停掉后，eureka会进入自我保护机制，不会立马就删除掉服务实例信息
 * 对于用zookeeper作为注册中心，注册上去的服务假如停掉，那么到了心跳时间后，该服务实例信息会被立马删除掉
 */
@EnableDiscoveryClient
public class PaymentMain8004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
