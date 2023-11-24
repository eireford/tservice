package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.PlaceEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PlaceService {

    Mono<PlaceEntity> save(PlaceEntity place);

    Mono<PlaceEntity> update(PlaceEntity place);

    Mono<Void> deleteById(UUID id);

    Mono<PlaceEntity> getById(UUID id);

    Flux<PlaceEntity> findAll();

    Flux<PlaceEntity> findByName(String name);

    Flux<PlaceEntity> findByValue(String value);
}
