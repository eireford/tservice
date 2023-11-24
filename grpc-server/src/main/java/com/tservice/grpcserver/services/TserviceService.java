package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.TserviceEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TserviceService {

    Mono<TserviceEntity> save(TserviceEntity tservice);

    Mono<TserviceEntity> update(TserviceEntity tservice);

    Mono<Void> deleteById(UUID id);

    Mono<TserviceEntity> getById(UUID id);

    Flux<TserviceEntity> findAll();

    Flux<TserviceEntity> findByName(String name);

    Flux<TserviceEntity> findByValue(String value);

}
