package com.tservice.grpcserver;

import com.github.javafaker.Faker;
import com.tservice.grpcserver.testconfig.TestConfig;
import com.tservice.sbg.config.FakerConfig;
import com.tservice.sbg.GrpcServerApplication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
//
//@RequiredArgsConstructor
//@SpringBootTest(classes = GrpcServerApplication.class)
//@AutoConfigureWebTestClient
//@ContextConfiguration(classes = {GrpcServerApplication.class,TestConfig.class})

public class ActuatorControllerTest {


    //private WebTestClient webTestClient;
    private Faker faker; // Inject the Faker bean

    @Test
    public void actuatorShouldBeStatusOk() {
        // Use the Faker instance to generate random data for your test
        String randomName = faker.name().fullName();
        // Perform your WebFlux test using the WebTestClient
        //webTestClient.get().uri("/actuator").exchange().expectStatus().isOk();
    }
}
