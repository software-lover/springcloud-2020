package com.wzm.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-31 9:47
 */
public interface LoadBalancer {

    /**
     *
     * @param serviceInstances
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
