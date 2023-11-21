package com.tservice.grpcserver.services;

import com.google.common.io.Files;
import com.tservice.grpcserver.entities.TspoonEntity;
import com.tservice.grpcserver.repositories.TspoonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TspoonServiceImpl implements TspoonService{

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private TspoonRepository tspoonRepository;

    @Override
    public Mono<TspoonEntity> save(TspoonEntity tspoon) {
        return tspoonRepository.save(tspoon).timeout(TIMEOUT);
    }

    @Override
    public Mono<TspoonEntity> update(TspoonEntity tspoon) {
        return tspoonRepository.save(tspoon).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return tspoonRepository.deleteById(uuid).timeout(TIMEOUT);
    }

    @Override
    public Mono<TspoonEntity> findById(UUID uuid) {
        return tspoonRepository.findById(uuid).timeout(TIMEOUT);
    }

    @Override
    public Flux<TspoonEntity> findAll() {
        return tspoonRepository.findAll().timeout(TIMEOUT);
    }

    @Override
    public Flux<TspoonEntity> findByName(String name) {
        return tspoonRepository.findByName(name).timeout(TIMEOUT);
    }

    @Override
    public Flux<TspoonEntity> findByValue(String value) {
        return tspoonRepository.findByValue(value).timeout(TIMEOUT);
    }

}
