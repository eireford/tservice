package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.UserContextEntity;
import com.tservice.grpcserver.repositories.UserContextRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserContextServiceImpl implements UserContextService{

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final UserContextRepository userContextRepository;

    @Override
    public Mono<UserContextEntity> save(UserContextEntity userContextEntity) {
        return userContextRepository.save(userContextEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<UserContextEntity> update(UserContextEntity userContext) {
        return userContextRepository.save(userContext).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return userContextRepository.deleteById(uuid).timeout(TIMEOUT);
    }

    @Override
    public Mono<UserContextEntity> findById(UUID uuid) {
        return userContextRepository.findById(uuid).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserContextEntity> findAll() {
        return userContextRepository.findAll().timeout(TIMEOUT);
    }

    @Override
    public Flux<UserContextEntity> findByUserId(UUID userId) {
        return userContextRepository.findByUserId(userId).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserContextEntity> findByContextId(UUID contextId) {
        return userContextRepository.findByContextId(contextId).timeout(TIMEOUT);
    }
}
