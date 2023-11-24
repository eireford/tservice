package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.UserIdentifierEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserIdentifierRepository extends ReactiveSortingRepository<UserIdentifierEntity, UUID> {

    Mono<UserIdentifierEntity> save(UserIdentifierEntity userIdentifier);

    Mono<Void> deleteById(UUID id);

    Mono<UserIdentifierEntity> getById(UUID id);

    Flux<UserIdentifierEntity> findByUserId(UUID userId);

    Flux<UserIdentifierEntity> findByIdentifierId(UUID identifierId);
}
