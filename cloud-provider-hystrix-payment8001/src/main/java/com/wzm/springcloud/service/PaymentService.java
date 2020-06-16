package com.wzm.springcloud.service;

import com.wzm.springcloud.entities.Payment;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 20:46
 */
public interface PaymentService {

    String paymentInfo_OK(Integer id);

    String paymentInfo_Timeout(Integer id);

    /**
     * 测试服务熔断
     * @param id
     * @return
     */
    String paymentCircuitBreaker(Integer id);
}
