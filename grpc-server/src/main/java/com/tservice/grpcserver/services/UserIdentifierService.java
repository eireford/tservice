package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.UserIdentifierEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserIdentifierService {

    Mono<UserIdentifierEntity> save(UserIdentifierEntity userIdentifier);

    Mono<UserIdentifierEntity> update(UserIdentifierEntity userIdentifier);

    Mono<Void> delete(UUID uuid);

    Mono<UserIdentifierEntity> findById(UUID uuid);

    Flux<UserIdentifierEntity> findAll();

    Flux<UserIdentifierEntity> findByUserId(UUID userId);

    Flux<UserIdentifierEntity> findByIdentifierId(UUID identifierId);
}
