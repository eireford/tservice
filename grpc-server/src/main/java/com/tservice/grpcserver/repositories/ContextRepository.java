package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.ContextEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ContextRepository extends ReactiveSortingRepository<ContextEntity, UUID> {
    //Mono<ContextEntity> update(ContextEntity context);

    //Mono<Void> delete(UUID uuid);

    Flux<ContextEntity> findByName(String name);

    Flux<ContextEntity> findByValue(String value);

    long deleteByUuid(UUID uuid);
}
