package com.wzm.springcloud.service.impl;

import com.wzm.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-13 12:39
 */

/**
 * 定义消息的推送管道——发送者
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String seria = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(seria).build());
        System.out.println("**********seria: " + seria);
        return null;
    }
}
