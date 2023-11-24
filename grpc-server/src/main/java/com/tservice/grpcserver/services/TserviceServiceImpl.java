package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.TserviceEntity;
import com.tservice.grpcserver.repositories.TserviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class TserviceServiceImpl implements TserviceService {

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final TserviceRepository tserviceRepository;

    @Override
    public Mono<TserviceEntity> save(TserviceEntity tserviceEntity) {
        return tserviceRepository.save(tserviceEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<TserviceEntity> update(TserviceEntity tservice) {
        return tserviceRepository.save(tservice).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return tserviceRepository.deleteById(id).timeout(TIMEOUT);
    }

    @Override
    public Mono<TserviceEntity> getById(UUID id) {
        return tserviceRepository.getById(id).timeout(TIMEOUT);
    }

    @Override
    public Flux<TserviceEntity> findAll() {
        return tserviceRepository.findAll(null).timeout(TIMEOUT);
    }

    @Override
    public Flux<TserviceEntity> findByName(String name) {
        return tserviceRepository.findByName(name).timeout(TIMEOUT);
    }

    @Override
    public Flux<TserviceEntity> findByValue(String value) {
        return tserviceRepository.findByValue(value).timeout(TIMEOUT);
    }
}
