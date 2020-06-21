package com.wzm.alibabacloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-21 10:52
 */
@RestController
@RequestMapping("/sentinel")
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "---->TestA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "---->TestB";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("--->testD 测试 RT");
        return "---->TestD";
    }
}
