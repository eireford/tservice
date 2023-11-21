package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.DomainTserviceEntity;
import com.tservice.grpcserver.repositories.DomainTserviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DomainTserviceServiceImpl implements DomainTserviceService{

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final DomainTserviceRepository domainTserviceRepository;

    @Override
    public Mono<DomainTserviceEntity> save(DomainTserviceEntity domainTserviceEntity) {
        return domainTserviceRepository.save(domainTserviceEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<DomainTserviceEntity> update(DomainTserviceEntity domainTservice) {
        return domainTserviceRepository.save(domainTservice).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return domainTserviceRepository.deleteById(uuid).timeout(TIMEOUT);
    }

    @Override
    public Mono<DomainTserviceEntity> findById(UUID uuid) {
        return domainTserviceRepository.findById(uuid).timeout(TIMEOUT);
    }

    @Override
    public Flux<DomainTserviceEntity> findAll() {
        return domainTserviceRepository.findAll().timeout(TIMEOUT);
    }

    @Override
    public Flux<DomainTserviceEntity> findByTserviceId(UUID tserviceId) {
        return domainTserviceRepository.findByTserviceId(tserviceId).timeout(TIMEOUT);
    }

    @Override
    public Flux<DomainTserviceEntity> findByDomainId(UUID domainId) {
        return domainTserviceRepository.findByDomainId(domainId).timeout(TIMEOUT);
    }
}
