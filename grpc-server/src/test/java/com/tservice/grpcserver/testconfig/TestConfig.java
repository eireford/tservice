package com.tservice.grpcserver.testconfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestConfiguration
public class TestConfig {
    @Bean
    public WebTestClient webTestClient() {
        return WebTestClient.bindToServer().baseUrl("http://localhost:8084").build();
    }
}
