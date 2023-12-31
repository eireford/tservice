package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.UserContextEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserContextRepository extends ReactiveSortingRepository<UserContextEntity, UUID> {

    Mono<UserContextEntity> save(UserContextEntity userContext);

    Mono<Void> deleteById(UUID id);

    Mono<UserContextEntity> getById(UUID id);

    Flux<UserContextEntity> findByUserId(UUID userId);

    Flux<UserContextEntity> findByContextId(UUID contextId);
}
