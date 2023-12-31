package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.DomainEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface DomainRepository extends ReactiveSortingRepository<DomainEntity, UUID> {

    Mono<DomainEntity> save(DomainEntity domain);

    Mono<Void> deleteById(UUID id);

    Mono<DomainEntity> getById(UUID id);

    Flux<DomainEntity> findByName(String name);

    Flux<DomainEntity> findByValue(String value);
}
