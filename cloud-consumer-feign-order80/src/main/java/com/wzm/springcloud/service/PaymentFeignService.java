package com.wzm.springcloud.service;

import com.wzm.springcloud.entities.CommonResult;
import com.wzm.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-31 10:33
 */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
@RequestMapping("/payment")
public interface PaymentFeignService {

    @GetMapping("/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/feign/timeout")
    CommonResult<String> paymentFeignTimeout();
}
