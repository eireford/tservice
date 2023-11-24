package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.ContextEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ContextService {
    Mono<ContextEntity> save(ContextEntity context);

    Mono<ContextEntity> update(ContextEntity context);

    Mono<Void> deleteById(UUID id);

    Mono<ContextEntity> getById(UUID id);

    Flux<ContextEntity> findAll();

    Flux<ContextEntity> findByName(String name);

    Flux<ContextEntity> findByValue(String value);
}
