package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.IdentifierEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface IdentifierRepository extends ReactiveSortingRepository<IdentifierEntity, UUID> {

    Mono<IdentifierEntity> save(IdentifierEntity identifierEntity);

    Mono<Void> deleteById(UUID id);

    Mono<IdentifierEntity> getById(UUID id);

    Flux<IdentifierEntity> findByName(String name);

    Flux<IdentifierEntity> findByValue(String value);
}
