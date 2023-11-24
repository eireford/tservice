package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.TserviceEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TserviceRepository extends ReactiveSortingRepository<TserviceEntity, UUID> {

    Mono<TserviceEntity> save(TserviceEntity tservice);

    Mono<Void> deleteById(UUID id);

    Mono<TserviceEntity> getById(UUID id);

    Flux<TserviceEntity> findByName(String name);

    Flux<TserviceEntity> findByValue(String value);
}
