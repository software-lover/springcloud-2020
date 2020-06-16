package com.wzm.springcloud.controller;

import com.wzm.springcloud.entities.CommonResult;
import com.wzm.springcloud.entities.Payment;
import com.wzm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 20:49
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("---->插入结果={}", result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功， serverPort：" + serverPort, result);
        }

        return new CommonResult(500, "插入数据库失败");
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("--->查询结果={}", payment);

        if (null != payment) {
            return new CommonResult<>(200, "查询成功， serverPort：" + serverPort, payment);
        }

        return new CommonResult<>(500, String.format("没有对应id=[%s]的记录", id));
    }

    /**
     * 服务发现信-服务信息描述
     * @return
     */
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = discoveryClient.getServices();
        log.info("--->list={}", list);

        List<ServiceInstance> srvList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance element : srvList) {
            log.info("serviceId={}, host={}, port={}, uri={}",
                    element.getServiceId(), element.getHost(), element.getPort(), element.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    public CommonResult<String> paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<>(200, "执行成功", serverPort);
    }
}
