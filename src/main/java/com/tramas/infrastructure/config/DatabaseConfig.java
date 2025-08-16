package com.tramas.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.tramas.infrastructure.adapter.persistence")
public class DatabaseConfig {
    // Spring Boot auto-configuration will handle ConnectionFactory creation
    // based on spring.r2dbc.url configuration
}