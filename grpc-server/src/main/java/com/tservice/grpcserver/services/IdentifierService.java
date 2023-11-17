package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.IdentifierEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IdentifierService {

    Mono<IdentifierEntity> save(IdentifierEntity identifierEntity);

    Mono<IdentifierEntity> update(IdentifierEntity identifierEntity);

    Mono<Void> delete(UUID uuid);

    Mono<IdentifierEntity> findById(UUID uuid);

    Flux<IdentifierEntity> findAll();

    Flux<IdentifierEntity> findByName(String name);

    Flux<IdentifierEntity> findByValue(String value);
}
