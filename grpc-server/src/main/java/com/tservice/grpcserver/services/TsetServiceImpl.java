package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.TsetEntity;
import com.tservice.grpcserver.repositories.TsetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Service
public class TsetServiceImpl implements TsetService{

    private TsetRepository tsetRepository;

    @Override
    public Mono<TsetEntity> save(TsetEntity tset) {
        return tsetRepository.save(tset);
    }

    @Override
    public Mono<TsetEntity> update(TsetEntity tset) {
        return tsetRepository.save(tset);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return tsetRepository.deleteById(uuid);
    }

    @Override
    public Mono<TsetEntity> findById(UUID uuid) {
        return tsetRepository.findById(uuid);
    }

    @Override
    public Flux<TsetEntity> findAll() {
        return tsetRepository.findAll();
    }

    @Override
    public Flux<TsetEntity> findByName(String name) {
        return tsetRepository.findByName(name);
    }

    @Override
    public Flux<TsetEntity> findByValue(String value) {
        return tsetRepository.findByValue(value);
    }

}
