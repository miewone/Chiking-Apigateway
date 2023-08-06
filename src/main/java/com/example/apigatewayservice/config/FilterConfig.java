package com.example.apigatewayservice.config;

import com.example.apigatewayservice.filter.LoggingFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wgPark on 2023-07-31.
 */
@Configuration
public class FilterConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, LoggingFilter loggingFilter){
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f ->
                                f.filter(loggingFilter)
                                        .addRequestHeader("first-request", "first-request-header")
                                .addResponseHeader("first-response","first-response-header"))
                        .uri("lb://FIRST-SERVICE"))
                .route(r -> r.path("/user-service/**")
                        .filters(f ->
                                f.filter(loggingFilter)
                                        .addRequestHeader("user-request","user-request-header")
                                        .addResponseHeader("user-response","user-response-haeder"))
                        .uri("lb://USER-SERVICE/")
                )
                .build();

    }
}