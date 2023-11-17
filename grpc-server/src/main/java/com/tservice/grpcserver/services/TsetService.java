package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.TsetEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TsetService {

    Mono<TsetEntity> save(TsetEntity tset);

    Mono<TsetEntity> update(TsetEntity tset);

    Mono<Void> delete(UUID uuid);

    Mono<TsetEntity> findById(UUID uuid);

    Flux<TsetEntity> findAll();

    Flux<TsetEntity> findByName(String name);

    Flux<TsetEntity> findByValue(String value);
}
