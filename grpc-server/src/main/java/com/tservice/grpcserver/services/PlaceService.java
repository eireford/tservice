package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.PlaceEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PlaceService {

        Mono<PlaceEntity> save(PlaceEntity place);

        Mono<PlaceEntity> update(PlaceEntity place);

        Mono<Void> delete(UUID uuid);

        Mono<PlaceEntity> findById(UUID uuid);

        Flux<PlaceEntity> findAll();

        Flux<PlaceEntity> findByName(String name);

        Flux<PlaceEntity> findByValue(String value);
}
