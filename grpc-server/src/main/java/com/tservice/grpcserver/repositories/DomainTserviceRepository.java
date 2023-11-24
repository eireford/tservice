package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.DomainTserviceEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface DomainTserviceRepository extends ReactiveSortingRepository<DomainTserviceEntity, UUID> {

    Mono<DomainTserviceEntity> save(DomainTserviceEntity domainTservice);

    Mono<Void> deleteById(UUID id);

    Mono<DomainTserviceEntity> getById(UUID id);

    Flux<DomainTserviceEntity> findByDomainId(UUID domainId);

    Flux<DomainTserviceEntity> findByTserviceId(UUID tserviceId);

}
