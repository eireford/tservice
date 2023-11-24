package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.PlaceEntity;
import com.tservice.grpcserver.repositories.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService {

    private static final Duration TIMEOUT = Duration.ofSeconds(5);

    private PlaceRepository placeRepository;

    @Override
    public Mono<PlaceEntity> save(PlaceEntity place) {
        return placeRepository.save(place).timeout(TIMEOUT);
    }

    @Override
    public Mono<PlaceEntity> update(PlaceEntity place) {
        return placeRepository.save(place).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return placeRepository.deleteById(id).timeout(TIMEOUT);
    }

    @Override
    public Mono<PlaceEntity> getById(UUID id) {
        return placeRepository.getById(id).timeout(TIMEOUT);
    }

    @Override
    public Flux<PlaceEntity> findAll() {
        return placeRepository.findAll(null).timeout(TIMEOUT);
    }

    @Override
    public Flux<PlaceEntity> findByName(String name) {
        return placeRepository.findByName(name).timeout(TIMEOUT);
    }

    @Override
    public Flux<PlaceEntity> findByValue(String value) {
        return placeRepository.findByValue(value).timeout(TIMEOUT);
    }

}
