package com.tservice.grpcserver.config;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfig {

    @Value(value = "${faker.locale:en}")
    private String locale = "en";

    @Bean
    Faker faker() {
        return new Faker();
    }
}
