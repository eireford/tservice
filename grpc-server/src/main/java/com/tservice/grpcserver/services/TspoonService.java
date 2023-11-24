package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.TspoonEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TspoonService {

    Mono<TspoonEntity> save(TspoonEntity tspoon);

    Flux<TspoonEntity> saveAll(Flux<TspoonEntity> tspoons);

    Mono<TspoonEntity> update(TspoonEntity tspoon);

    Mono<Void> deleteById(UUID id);

    Mono<TspoonEntity> getById(UUID id);

    Flux<TspoonEntity> findAll();

    Flux<TspoonEntity> findByName(String name);

    Flux<TspoonEntity> findByValue(String value);

}
