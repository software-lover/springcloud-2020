package com.wzm.alibabacloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wzm.springcloud.entities.CommonResult;
import com.wzm.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-14 16:09
 */
@RestController
@RequestMapping("/consumer/payment/nacos")
@Slf4j
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/fallback/{id}")
    @SentinelResource(value = "fallback")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(serverURL + "/payment/paymentSQL/" + id, CommonResult.class);
        if (4 == id) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常....");
        } else if (null == result.getData()) {
            throw new NullPointerException("NullPointerException, 该id没有对应记录，控制住异常");
        }

        return result;
    }
}
