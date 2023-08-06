package com.example.apigatewayservice.filter;

import com.example.apigatewayservice.filter.properties.CustomGlobalFilterProperties;
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
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    private final CustomGlobalFilterProperties properties;

    public CustomGlobalFilter(CustomGlobalFilterProperties properties){
        this.properties = properties;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("CustomGlobalFilter baseMessage");
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if(properties.isPreLogger()){
            log.info(("Global Filter Start: request id -> {}"),request.getId());
        }
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            if(properties.isPostLogger()){
                log.info("Global Filter End : response code -> {}", response.getStatusCode());
            }
        }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
