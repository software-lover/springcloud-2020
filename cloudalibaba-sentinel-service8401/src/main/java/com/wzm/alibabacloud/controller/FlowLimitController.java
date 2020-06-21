package com.wzm.alibabacloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey", blockHandler = "deal_testHotkey")
    public String testHotkey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "---->testHotkey";
    }

    public String deal_testHotkey(String p1, String p2, BlockException exception) {
        // sentinel 系统默认提示：Blocked by Sentinel(flow limiting)
        return "---->deal_testHotkeyo(╥﹏╥)o";
    }
}
