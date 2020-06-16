package com.wzm.springcloud.service.impl;

import com.wzm.springcloud.dao.PaymentDao;
import com.wzm.springcloud.entities.Payment;
import com.wzm.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wzm
 * @description
 * @date 2020-05-27 20:47
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
