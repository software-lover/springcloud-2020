package com.wzm.springcloud.controller;

import com.wzm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-31 13:06
 */
@RestController
@RequestMapping("/payment/hystrix")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("--->result={}", result);

        return result;
    }

    @GetMapping("/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("--->result={}", result);

        return result;
    }

    /**
     * 服务熔断次数：
     *
     * 1、先输入正数测试程序是否正常：http://localhost:8001/payment/hystrix/cb/1   此时返回正常的提示结果信息
     * 2、然后输入负数：http://localhost:8001/payment/hystrix/cb/-1  此时返回降级的逻辑信息
     * 3、之后连续快速不断的请求参数为负数，此时出现的结果都是降级的逻辑
     * 4、第3步之后立马切换为请求正数的参数，此时会发现返回的结果还是降级的逻辑信息，这就说明服务被熔断了，过了一会之后，又会返回正常的结果
     *
     * 这就说明了，在10000(10s)这个时间窗口期内，10次请求有6次失败，那么就【开启】断路器，返回降级的逻辑信息；
     * 然后会切换断路器为【半开】状态，尝试通过小部分信息，去检测服务是否可用
     * 假如服务可用，那么会切换断路器为【关闭】状态
     *
     *
     * @param id
     * @return
     */
    @GetMapping("/cb/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("--->result={}", result);

        return result;
    }
}
