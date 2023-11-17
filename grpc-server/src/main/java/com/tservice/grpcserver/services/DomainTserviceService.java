package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.DomainTserviceEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface DomainTserviceService {

    Mono<DomainTserviceEntity> save(DomainTserviceEntity domainTservice);

    Mono<DomainTserviceEntity> update(DomainTserviceEntity domainTservice);

    Mono<Void> delete(UUID uuid);

    Mono<DomainTserviceEntity> findById(UUID uuid);

    Flux<DomainTserviceEntity> findAll();

    Flux<DomainTserviceEntity> findByDomainId(UUID domainId);

    Flux<DomainTserviceEntity> findByTserviceId(UUID tserviceId);
}
