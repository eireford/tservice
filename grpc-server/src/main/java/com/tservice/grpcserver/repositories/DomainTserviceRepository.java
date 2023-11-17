package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.DomainTserviceEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface DomainTserviceRepository extends ReactiveSortingRepository<DomainTserviceEntity, UUID> {

    Mono<DomainTserviceEntity> update(DomainTserviceEntity domainTservice);

    Mono<Void> delete(UUID id);

    Flux<DomainTserviceEntity> findByDomainId(UUID domainId);

    Flux<DomainTserviceEntity> findByTserviceId(UUID tserviceId);
}
