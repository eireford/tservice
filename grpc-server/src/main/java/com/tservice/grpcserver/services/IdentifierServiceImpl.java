package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.IdentifierEntity;
import com.tservice.grpcserver.repositories.IdentifierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class IdentifierServiceImpl implements IdentifierService {

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private IdentifierRepository identifierRepository;

    @Override
    public Mono<IdentifierEntity> save(IdentifierEntity identifierEntity) {
        return identifierRepository.save(identifierEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<IdentifierEntity> update(IdentifierEntity identifierEntity) {
        return identifierRepository.save(identifierEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return identifierRepository.deleteById(id).timeout(TIMEOUT);
    }

    @Override
    public Mono<IdentifierEntity> getById(UUID id) {
        return identifierRepository.getById(id).timeout(TIMEOUT);
    }

    @Override
    public Flux<IdentifierEntity> findAll() {
        return identifierRepository.findAll(null).timeout(TIMEOUT);
    }

    @Override
    public Flux<IdentifierEntity> findByName(String name) {
        return identifierRepository.findByName(name).timeout(TIMEOUT);
    }

    @Override
    public Flux<IdentifierEntity> findByValue(String value) {
        return identifierRepository.findByValue(value).timeout(TIMEOUT);
    }


}
