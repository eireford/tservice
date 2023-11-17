package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.TspoonEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TspoonService {

        Mono<TspoonEntity> save(TspoonEntity tspoon);

        Mono<TspoonEntity> update(TspoonEntity tspoon);

        Mono<Void> delete(UUID uuid);

        Mono<TspoonEntity> findById(UUID uuid);

        Flux<TspoonEntity> findAll();

        Flux<TspoonEntity> findByName(String name);

        Flux<TspoonEntity> findByValue(String value);

}
