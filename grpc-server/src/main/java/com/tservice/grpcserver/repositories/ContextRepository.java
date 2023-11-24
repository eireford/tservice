package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.ContextEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ContextRepository extends ReactiveSortingRepository<ContextEntity, UUID> {

    Mono<ContextEntity> save(ContextEntity context);

    Mono<Void> deleteById(UUID id);

    Mono<ContextEntity> getById(UUID id);

    Flux<ContextEntity> findByName(String name);

    Flux<ContextEntity> findByValue(String value);
    
}
