package com.wzm.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangzhimin
 * @description
 * @date 2020-05-30 22:31
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        // 随机
        return new RandomRule();
    }
}
