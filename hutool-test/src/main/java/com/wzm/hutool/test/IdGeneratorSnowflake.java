package com.wzm.hutool.test;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangzhimin
 * @description
 * @date 2020-08-09 12:24
 *
 * 雪花算法会存在时钟回拨的问题，导致有些id可能会重复
 *
 * 可以使用
 *  百度开元的分布式唯一ID生成器 UidGenerator
 *  Leaf——美团点评分布式ID生成器
 *
 */
@Slf4j
public class IdGeneratorSnowflake {

    private long workerId = 0;
    private long datacenterId = 1;

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    public void init() {
        String localhostStr = NetUtil.getLocalhostStr();
        try {
            workerId = NetUtil.ipv4ToLong(localhostStr);
            log.info("当前机器【{}】的workerId={}", localhostStr, workerId);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("当前机器的workerId获取失败", e);
            workerId = localhostStr.hashCode();
        }
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        IdGeneratorSnowflake idGeneratorSnowflake = new IdGeneratorSnowflake();
        idGeneratorSnowflake.init();
        long snowflakeId = idGeneratorSnowflake.snowflakeId();
        System.out.println(snowflakeId);
    }
}
