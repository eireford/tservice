package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.PlaceEntity;
import com.tservice.grpcserver.repositories.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.UUID;

@AllArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService{

    private PlaceRepository placeRepository;

    @Override
    public Mono<PlaceEntity> save(PlaceEntity place) {
        return placeRepository.save(place);
    }

    @Override
    public Mono<PlaceEntity> update(PlaceEntity place) {
        return placeRepository.save(place);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return placeRepository.deleteById(uuid);
    }

    @Override
    public Mono<PlaceEntity> findById(UUID uuid) {
        return placeRepository.findById(uuid);
    }

    @Override
    public Flux<PlaceEntity> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Flux<PlaceEntity> findByName(String name) {
        return placeRepository.findByName(name);
    }

    @Override
    public Flux<PlaceEntity> findByValue(String value) {
        return placeRepository.findByValue(value);
    }

}
