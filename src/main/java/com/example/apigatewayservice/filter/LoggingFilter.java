package com.example.apigatewayservice.filter;

import com.example.apigatewayservice.filter.properties.LoggingFilterProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
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
public class LoggingFilter implements GatewayFilter {
    private final LoggingFilterProperties properties;

    public LoggingFilter(LoggingFilterProperties properties){
        this.properties = properties;
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();;
        ServerHttpResponse response = exchange.getResponse();

        log.info("Logging filter baseMessage: "+ properties.getBaseMessage());

        if(properties.isPreLogger()){
            log.info(("Logging Filter Start: request id -> {}"),request.getId());
        }
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            if(properties.isPostLogger()){
                log.info("Logging Filter End : response code -> {}", response.getStatusCode());
            }
        }));

    }

}
