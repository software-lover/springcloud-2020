package com.wzm.springcloud.controller;

import com.wzm.springcloud.entities.CommonResult;
import com.wzm.springcloud.entities.Payment;
import com.wzm.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 21:36
 */
@RestController
@RequestMapping("/consumer/payment")
@Slf4j
public class OrderController {

    /**
     * private static final String PAYMENT_URL = "http://localhost:8001";
     */
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        // return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class).getBody();
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        // return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommonResult<>(444, "操作失败");
    }

    /**
     * 测试@EnableDiscoveryClient,消费端可以调用服务发现
     * @return
     */
    @RequestMapping(value = "/discovery")
    public Object discovery() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/discovery", Object.class);
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (CollectionUtils.isEmpty(instances)) {
            return null;
        }

        ServiceInstance instance = loadBalancer.instances(instances);
        return restTemplate.getForObject(instance.getUri() + "/payment/lb", String.class);
    }


    /**
     * sleuth+zipkin 服务调用链路监控
     */
    @GetMapping("/zipkin")
    public String zipkin() {
        String result = restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
        return result;
    }
}
