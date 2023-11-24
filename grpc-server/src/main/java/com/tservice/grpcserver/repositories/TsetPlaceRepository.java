package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.TsetPlaceEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TsetPlaceRepository extends ReactiveSortingRepository<TsetPlaceEntity, UUID> {

    Mono<TsetPlaceEntity> save(TsetPlaceEntity tsetPlace);

    Mono<Void> deleteById(UUID id);

    Mono<TsetPlaceEntity> getById(UUID id);

    Flux<TsetPlaceEntity> findByTsetId(UUID tsetId);

    Flux<TsetPlaceEntity> findByPlaceId(UUID placeId);
}
