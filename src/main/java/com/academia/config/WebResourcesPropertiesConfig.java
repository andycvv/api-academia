package com.academia.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebResourcesPropertiesConfig {
    @Bean
    public WebProperties.Resources getWebResourcesPropertiesConfig() {
        return new WebProperties.Resources();
    }
}
