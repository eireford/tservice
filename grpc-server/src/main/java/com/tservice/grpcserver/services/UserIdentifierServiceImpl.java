package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.UserIdentifierEntity;
import com.tservice.grpcserver.repositories.UserIdentifierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserIdentifierServiceImpl implements UserIdentifierService {

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final UserIdentifierRepository userIdentifierRepository;

    @Override
    public Mono<UserIdentifierEntity> save(UserIdentifierEntity userIdentifierEntity) {
        return userIdentifierRepository.save(userIdentifierEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<UserIdentifierEntity> update(UserIdentifierEntity userIdentifier) {
        return userIdentifierRepository.save(userIdentifier).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return userIdentifierRepository.deleteById(id).timeout(TIMEOUT);
    }

    @Override
    public Mono<UserIdentifierEntity> getById(UUID id) {
        return userIdentifierRepository.getById(id).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserIdentifierEntity> findAll() {
        return userIdentifierRepository.findAll(null).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserIdentifierEntity> findByUserId(UUID userId) {
        return userIdentifierRepository.findByUserId(userId).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserIdentifierEntity> findByIdentifierId(UUID identifierId) {
        return userIdentifierRepository.findByIdentifierId(identifierId).timeout(TIMEOUT);
    }
}
