package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.PlaceEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Repository
public interface PlaceRepository extends ReactiveSortingRepository<PlaceEntity, UUID> {

    Mono<PlaceEntity> update(PlaceEntity place);

    Mono<Void> delete(UUID id);

    Flux<PlaceEntity> findByName(String name);

    Flux<PlaceEntity> findByValue(String value);

}
