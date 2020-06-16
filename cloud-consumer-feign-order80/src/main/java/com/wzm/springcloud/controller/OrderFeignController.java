package com.wzm.springcloud.controller;

import com.wzm.springcloud.entities.CommonResult;
import com.wzm.springcloud.entities.Payment;
import com.wzm.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-31 10:37
 */
@RestController
@RequestMapping("/consumer/payment")
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/feign/timeout")
    public CommonResult<String> paymentFeignTimeout() {
        // 客户端默认等待1s
        return paymentFeignService.paymentFeignTimeout();
    }
}
