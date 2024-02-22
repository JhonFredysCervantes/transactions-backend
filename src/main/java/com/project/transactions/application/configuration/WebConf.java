package com.project.transactions.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration
 */
@Configuration
public class WebConf implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;
    private final String[] allowedOrigins;

    /**
     * Constructor
     *
     * @param allowedOrigins Allowed origins
     */
    public WebConf(@Value("${web.config.allowed-origins}") String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(MAX_AGE_SECS);
    }
}
