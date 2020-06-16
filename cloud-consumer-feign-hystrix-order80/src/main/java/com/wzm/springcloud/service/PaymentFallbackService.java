package com.wzm.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-31 14:54
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back paymentInfo_OK，o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "-----PaymentFallbackService fall back paymentInfo_Timeout，o(╥﹏╥)o";
    }
}
