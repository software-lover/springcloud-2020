package com.wzm.alibabacloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wzm.alibabacloud.service.PaymentService;
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
    // 没有配置
//    @SentinelResource(value = "fallback")
    // fallback只负责业务异常
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
    // blockHandler只负责sentinel控制台配置违规
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    // 两个都配置了，则被限流降级而抛出BlockException时只会进入blockHandler处理逻辑；其他的业务异常进入handlerFallback处理逻辑
//    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
        // 表示在业务逻辑里面出现IllegalArgumentException异常时，handlerFallback将不起作用
        exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(serverURL + "/payment/paymentSQL/" + id, CommonResult.class);
        if (4 == id) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常....");
        } else if (null == result.getData()) {
            throw new NullPointerException("NullPointerException, 该id没有对应记录，控制住异常");
        }

        return result;
    }

    //========================OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/openfeign/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }

    /**
     * 本例是fallback
     */
    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "兜底异常handlerFallback， exception内容" + e.getMessage(), payment);
    }

    /**
     * 本例是blockHandler
     */
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(445, "兜底异常blockHandler-sentinel限流，无此流水， BlockException"
                + blockException.getMessage(), payment);
    }
}
