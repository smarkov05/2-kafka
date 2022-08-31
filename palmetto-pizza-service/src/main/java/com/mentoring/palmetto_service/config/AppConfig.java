package com.mentoring.palmetto_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }
}
