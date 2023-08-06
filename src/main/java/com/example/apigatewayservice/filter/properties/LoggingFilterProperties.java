package com.example.apigatewayservice.filter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wgPark on 2023-07-31.
 */
@Data
@ConfigurationProperties("custom.logging.filter")
public class LoggingFilterProperties {
    private String baseMessage;
    private boolean preLogger;
    private boolean postLogger;
}
