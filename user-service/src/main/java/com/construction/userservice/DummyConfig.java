package com.construction.userservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyConfig {
    @Bean
    public String dummyBean() {
        return "hello";
    }
}
