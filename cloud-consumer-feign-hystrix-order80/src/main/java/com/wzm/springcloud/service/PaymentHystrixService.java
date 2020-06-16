package com.wzm.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-31 13:37
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)

/**
 * 注意：配置了全局的服务降级类 PaymentFallbackService 之后，因为 PaymentFallbackService 实现了 PaymentHystrixService 接口，
 * 所以这里就不能再使用 @RequestMapping 注解了，需要把对应的请求URI前缀放到具体方法上去，不然启动会报 Ambiguous mapping. Cannot map....的异常
 */
//@RequestMapping("/payment/hystrix")
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id);
}
