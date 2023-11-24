package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.TsetPlaceEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TsetPlaceService {

    Mono<TsetPlaceEntity> save(TsetPlaceEntity tsetPlace);

    Mono<TsetPlaceEntity> update(TsetPlaceEntity tsetPlace);

    Mono<Void> deleteById(UUID id);

    Mono<TsetPlaceEntity> getById(UUID id);

    Flux<TsetPlaceEntity> findAll();

    Flux<TsetPlaceEntity> findByTsetId(UUID tsetId);

    Flux<TsetPlaceEntity> findByPlaceId(UUID placeId);
}
