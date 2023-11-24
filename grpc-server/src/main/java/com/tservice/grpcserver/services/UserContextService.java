package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.UserContextEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserContextService {

    Mono<UserContextEntity> save(UserContextEntity userContext);

    Mono<UserContextEntity> update(UserContextEntity userContext);

    Mono<Void> deleteById(UUID id);

    Mono<UserContextEntity> getById(UUID id);

    Flux<UserContextEntity> findAll();

    Flux<UserContextEntity> findByUserId(UUID userId);

    Flux<UserContextEntity> findByContextId(UUID contextId);
}
