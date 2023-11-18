package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.IdentifierEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Repository
public interface IdentifierRepository extends ReactiveSortingRepository<IdentifierEntity, UUID> {
    Mono<IdentifierEntity> update(IdentifierEntity identifierEntity);
    
    Mono<Void> delete(UUID uuid);

    Flux<IdentifierEntity> findByName(String name);

    Flux<IdentifierEntity> findByValue(String value);
}
