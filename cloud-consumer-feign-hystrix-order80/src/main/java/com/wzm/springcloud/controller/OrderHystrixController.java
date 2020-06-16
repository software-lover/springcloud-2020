package com.wzm.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wzm.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-31 13:37
 */
@RestController
@RequestMapping("/consumer/payment/hystrix")
@Slf4j
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("--->result={}", result);

        return result;
    }

    @GetMapping("/timeout/{id}")
    /**
     * 表示最多3秒钟就会超时，执行降级逻辑 paymentInfo_TimeoutHandler
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        // 假如一上来就执行报错，那么也会立即执行降级逻辑 paymentInfo_TimeoutHandler
//        int age = 10/0;

        String result = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("--->result={}", result);

        return result;
    }

    private String paymentInfo_TimeoutHandler(Integer id) {
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己，o(╥﹏╥)o";
    }
}
