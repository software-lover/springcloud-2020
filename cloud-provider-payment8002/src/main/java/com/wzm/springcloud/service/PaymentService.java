package com.wzm.springcloud.service;

import com.wzm.springcloud.entities.Payment;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 20:46
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
