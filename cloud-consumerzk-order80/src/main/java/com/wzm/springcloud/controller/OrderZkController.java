package com.wzm.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wzm
 * @description
 * @date 2020-05-30 17:30
 */
@RestController
@RequestMapping("/consumer/payment")
@Slf4j
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud-paymentzk-service";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/zk")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
    }
}
