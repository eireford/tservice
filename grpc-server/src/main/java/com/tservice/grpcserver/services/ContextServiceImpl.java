package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.ContextEntity;
import com.tservice.grpcserver.repositories.ContextRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ContextServiceImpl implements ContextService{

    private ContextRepository contextRepository;

    @Override
    public Mono<ContextEntity> save(ContextEntity context) {
        return contextRepository.save(context);
    }

    @Override
    public Mono<ContextEntity> update(ContextEntity context) {
        return contextRepository.update(context);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return contextRepository.delete(id);
    }

    @Override
    public Mono<ContextEntity> findById(UUID uuid){
        return contextRepository.findById(id);
    }

    @Override
    public Flux<ContextEntity> findAll() {
        return contextRepository.findAll();
    }

    @Override
    public Flux<ContextEntity> findByName(String name) {
        return contextRepository.findByName(name);
    }

    @Override
    public Flux<ContextEntity> findByValue(String value) {
        return contextRepository.findByValue(value);
    }
}
