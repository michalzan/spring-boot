package com.example.demo.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String appName;
    private String defaultGreeting;
    private String jsonPlaceholderBaseUrl;

}
