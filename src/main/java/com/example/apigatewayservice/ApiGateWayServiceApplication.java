package com.example.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApiGateWayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayServiceApplication.class, args);
    }

}
