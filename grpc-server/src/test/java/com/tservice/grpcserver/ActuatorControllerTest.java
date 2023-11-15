package com.tservice.grpcserver;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
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
