package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.TsetPlaceEntity;
import com.tservice.grpcserver.repositories.TsetPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TsetPlaceImpl implements TsetPlaceService{

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final TsetPlaceRepository tsetPlaceRepository;

    @Override
    public Mono<TsetPlaceEntity> save(TsetPlaceEntity tsetPlaceEntity) {
        return tsetPlaceRepository.save(tsetPlaceEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<TsetPlaceEntity> update(TsetPlaceEntity tsetPlace) {
        return tsetPlaceRepository.save(tsetPlace).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return tsetPlaceRepository.deleteById(uuid).timeout(TIMEOUT);
    }

    @Override
    public Mono<TsetPlaceEntity> findById(UUID uuid) {
        return tsetPlaceRepository.findById(uuid).timeout(TIMEOUT);
    }

    @Override
    public Flux<TsetPlaceEntity> findAll() {
        return tsetPlaceRepository.findAll().timeout(TIMEOUT);
    }

    @Override
    public Flux<TsetPlaceEntity> findByTsetId(UUID tsetPlaceId) {
        return tsetPlaceRepository.findByTsetId(tsetPlaceId).timeout(TIMEOUT);
    }

    @Override
    public Flux<TsetPlaceEntity> findByPlaceId(UUID placeId) {
        return tsetPlaceRepository.findByPlaceId(placeId).timeout(TIMEOUT);
    }

}
