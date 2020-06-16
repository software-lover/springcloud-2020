package com.wzm.springcloud;

import java.time.ZonedDateTime;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-06 18:22
 */
public class test {

    public static void main(String[] args) {

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        // 2020-06-06T18:22:56.545+08:00[Asia/Shanghai]
        System.out.println(zonedDateTime);
    }
}
