package com.madou.geapigateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //请求日志
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求信息标识"+request.getId());
        log.info("请求信息cookies"+request.getCookies());
        log.info("请求信息来源地址"+request.getRemoteAddress());
        log.info("请求信息参数"+request.getQueryParams());
        String hostString = request.getLocalAddress().getHostString();
        log.info("请求信息getLocalAddress()"+request.getLocalAddress().getHostString());
        log.info("请求信息request.getPath()"+request.getPath());
        log.info("custom global filter"+exchange);
        //设置白名单
        if (!IP_WHITE_LIST.contains(hostString)){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
