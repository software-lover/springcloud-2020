package com.wzm.alibabacloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-13 16:31
 */

@RestController
@RequestMapping("/payment/nacos")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry, serverport: " + serverPort + "\t id: " + id;
    }
}
