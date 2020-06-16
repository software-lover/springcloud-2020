package com.wzm.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author wangzhimin
 * @description
 * @date 2020-06-06 19:02
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("--->come in MyLogGatewayFilter: {}", new Date());

        String uname = exchange.getRequest().getQueryParams().getFirst("username");

        if (StringUtils.isBlank(uname)) {
            log.info("----->用户名为空，非法用户，o(╥﹏╥)o");

            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        // 放行到下一个过滤器
        return chain.filter(exchange);
    }

    /**
     * 加载过滤器的顺序，值越小优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
