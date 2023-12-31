package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.DomainEntity;
import com.tservice.grpcserver.repositories.DomainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DomainServiceImpl implements DomainService {

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final DomainRepository domainRepository;

    @Override
    public Mono<DomainEntity> save(DomainEntity domainEntity) {
        return domainRepository.save(domainEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<DomainEntity> update(DomainEntity domain) {
        return domainRepository.save(domain).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return domainRepository.deleteById(id).timeout(TIMEOUT);
    }

    @Override
    public Mono<DomainEntity> getById(UUID id) {
        return domainRepository.getById(id).timeout(TIMEOUT);
    }

    @Override
    public Flux<DomainEntity> findAll() {
        return domainRepository.findAll(null).timeout(TIMEOUT);
    }

    @Override
    public Flux<DomainEntity> findByName(String name) {
        return domainRepository.findByName(name).timeout(TIMEOUT);
    }

    @Override
    public Flux<DomainEntity> findByValue(String value) {
        return domainRepository.findByValue(value).timeout(TIMEOUT);
    }
}
