package com.tservice.grpcserver;

import com.tservice.grpcserver.repositories.TspoonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import com.tservice.grpcserver.entities.TspoonEntity;


@SpringBootTest(classes = InfrastructureConfiguration.class)
class TspoonRepositoryIntegrationTests {

    @Autowired
    TspoonRepository tspoonRepository;

    @Autowired
    DatabaseClient databaseClient;

    @BeforeEach
    void setUp() {

        Hooks.onOperatorDebug();

        var statements = Arrays.asList(//
                "DROP TABLE IF EXISTS tspoons;",
                "CREATE TABLE tspoons (id SERIAL PRIMARY KEY, username VARCHAR(256));"
        );

        statements.forEach(it -> databaseClient.sql(it) //
                .fetch() //
                .rowsUpdated() //
                .as(StepVerifier::create) //
                .expectNextCount(1) //
                .verifyComplete());

    }

    @Test
    void executeInsert() throws IOException {

        var tspoon = TspoonEntity.builder().build();

        tspoonRepository.save(tspoon) //
                .as(StepVerifier::create) //
                .expectNextMatches(actual -> {
                    System.out.println(actual);
                    return actual.getId() != null;
                }) //
                .verifyComplete();
    }


    private void insertTspoons(TspoonEntity... tspoons) {

        tspoonRepository.saveAll(Flux.just(Arrays.asList(tspoons)) //
                .as(StepVerifier::create) //
                .expectNextMatches(actual -> {
                    System.out.println(actual);
                    return actual.size() == tspoons.length;
                }) //
                .verifyComplete();
    }

}