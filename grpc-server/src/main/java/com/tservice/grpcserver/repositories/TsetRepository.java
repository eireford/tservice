package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.TsetEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TsetRepository extends ReactiveSortingRepository<TsetEntity, UUID> {

    Mono<TsetEntity> save(TsetEntity tset);

    Mono<Void> deleteById(UUID id);

    Mono<TsetEntity> getById(UUID id);

    Flux<TsetEntity> findByName(String name);

    Flux<TsetEntity> findByValue(String value);
}
