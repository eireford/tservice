package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.DomainEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface DomainService {
    
    Mono<DomainEntity> save(DomainEntity domain);
    
    Mono<DomainEntity> update(DomainEntity domain);
    
    Mono<Void> delete(UUID uuid);

    Mono<DomainEntity> findById(UUID uuid);

    Flux<DomainEntity> findAll();

    Flux<DomainEntity> findByName(String name);

    Flux<DomainEntity> findByValue(String value);
}
