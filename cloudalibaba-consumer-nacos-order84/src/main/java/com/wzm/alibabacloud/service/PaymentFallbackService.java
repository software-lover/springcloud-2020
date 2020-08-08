package com.wzm.alibabacloud.service;

import com.wzm.springcloud.entities.CommonResult;
import com.wzm.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @description 
 * @author json
 * @date 2020-08-08 12:03
 * @since 
 */
@Component
public class PaymentFallbackService implements PaymentService {
    
    /**
     * @param id
     * @description
     * @author json
     * @date 2020-08-08 12:03
     * @since
     */
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
