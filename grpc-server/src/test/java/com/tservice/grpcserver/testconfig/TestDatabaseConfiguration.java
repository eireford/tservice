package com.tservice.grpcserver.testconfig;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

@Configuration
public class TestDatabaseConfiguration {
    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        return new ConnectionFactoryInitializer();
    }
}
