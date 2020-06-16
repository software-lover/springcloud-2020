package com.wzm.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-31 14:28
 */
@RestController
@RequestMapping("/consumer/payment/hystrix")
@Slf4j
/**
 * 配置全局的降级方法，避免对每个业务逻辑方法都配置对应的降级方法，避免代码膨胀
 */
@DefaultProperties(defaultFallback = "globalFallbackMethod")
public class OrderHystrixController2 {

    @GetMapping("/globalFallback")
    @HystrixCommand
    public String testGlobalFallback() {
        int age = 10 / 0;
        return "success，O(∩_∩)O哈哈~";
    }

    private String globalFallbackMethod() {
        return "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }
}
