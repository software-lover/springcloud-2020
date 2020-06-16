package com.wzm.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wzm.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 20:47
 */
@Service
public class PaymentServiceImpl implements PaymentService {


    // =====================================================服务降级===================================================


    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK, id：" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 表示最多3秒钟就会超时，执行降级逻辑 paymentInfo_TimeoutHandler
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @Override
    public String paymentInfo_Timeout(Integer id) {
        // 假如一上来就执行报错，那么也会立即执行降级逻辑 paymentInfo_TimeoutHandler
//        int age = 10/0;

        int timeNumber = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_Timeout, id：" + id + "\t" + "O(∩_∩)O哈哈~ 耗时" + timeNumber + "秒钟";
    }

    private String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "系统繁忙或者运行报错，请稍后再试, id：" + id + "\t" + "w(ﾟДﾟ)w";
    }





    // =========================================================服务熔断===============================================



    /**
     * 测试服务熔断
     *
     * @param id
     * @return
     *
     * 配置参数名称参考：
     * {@link com.netflix.hystrix.HystrixCommandProperties}
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 快照窗口期：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 请求总数阈值：在快照窗口期内，必须满足请求总数阈值才有资格熔断，默认为20，意味着在10秒内，如果该hystrix命令的调用
            // 次数不足20次，即使所有的请求都超时或者其他原因失败，断路器都不会打开
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 错误百分百阈值：当请求总数在快照窗口期内超过了阈值，比如10秒内发生了30次调用，如果在这30次调用中，有15次发生了超时异常，
            // 也就是超过50%的错误百分比，在默认设定50%阈值情况下，这时候断路器打开
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            // 上面配置的意思是：在10000(10s)这个时间窗口期内，10次请求有6次失败，那么就开启断路器，返回降级的逻辑信息
    })
    @Override
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("---->id不能为负数");
        }

        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号=" + serialNumber;
    }

    private String paymentCircuitBreaker_fallback(Integer id) {
        return "id不能为负数，请稍后重试，o(╥﹏╥)o， id=" + id;
    }
}
