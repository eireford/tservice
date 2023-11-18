package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.IdentifierEntity;
import com.tservice.grpcserver.repositories.IdentifierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Service
public class IdentifierServiceImpl implements IdentifierService{

    private IdentifierRepository identifierRepository;

    @Override
    public Mono<IdentifierEntity> save(IdentifierEntity identifierEntity) {
        return identifierRepository.save(identifierEntity);
    }

    @Override
    public Mono<IdentifierEntity> update(IdentifierEntity identifierEntity) {
        return identifierRepository.update(identifierEntity);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return identifierRepository.delete(uuid);
    }

    @Override
    public Mono<IdentifierEntity> findById(UUID uuid){
        return identifierRepository.findById(uuid);
    }

    @Override
    public Flux<IdentifierEntity> findAll() {
        return identifierRepository.findAll();
    }

    @Override
    public Flux<IdentifierEntity> findByName(String name) {
        return identifierRepository.findByName(name);
    }

    @Override
    public Flux<IdentifierEntity> findByValue(String value) {
        return identifierRepository.findByValue(value);
    }


}
