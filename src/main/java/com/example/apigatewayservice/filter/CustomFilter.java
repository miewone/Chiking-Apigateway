package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by wgPark on 2023-07-31.
 */
@Component
@Slf4j
public class CustomFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        log.info("Custom PRE filter: request id -> {}", request.getId());

        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            log.info("Custom POST filter: response code -> {}", response.getStatusCode());
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
