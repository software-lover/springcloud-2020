package com.wzm.alibabacloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wzm.springcloud.entities.CommonResult;
import com.wzm.springcloud.entities.Payment;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-21 15:43
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "按客户自定义，global handlerException-------------1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "按客户自定义，global handlerException-------------2");
    }
}
