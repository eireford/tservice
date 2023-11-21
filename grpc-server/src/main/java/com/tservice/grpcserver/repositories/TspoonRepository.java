package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.TspoonEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TspoonRepository extends ReactiveSortingRepository<TspoonEntity, UUID> {

    //Mono<TspoonEntity> updateById(UUID tspoonId);

    //Mono<Void> delete(String tspoonId);

    Flux<TspoonEntity> findByName(String tspoonName);

    Flux<TspoonEntity> findByValue(String tspoonValue);
}
