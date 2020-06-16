package com.wzm.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 21:40
 */
@Configuration
public class ApplcationContextConfig {

    @Bean
    /**
     * 添加负载均衡的能力，这里在Controller里面才可以使用服务名称的方式进行服务提供者集群的调用
     */
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
