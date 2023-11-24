package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.UserEntity;
import com.tservice.grpcserver.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final UserRepository userRepository;

    @Override
    public Mono<UserEntity> save(UserEntity userEntity) {
        return userRepository.save(userEntity).timeout(TIMEOUT);
    }

    @Override
    public Mono<UserEntity> update(UserEntity user) {
        return userRepository.save(user).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> deleteById(UUID userId) {
        return userRepository.deleteById(userId);
    }

    @Override
    public Mono<UserEntity> getById(UUID userId) {
        return userRepository.getById(userId).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserEntity> findAll() {
        return userRepository.findAll(null).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserEntity> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName).timeout(TIMEOUT);
    }

    @Override
    public Flux<UserEntity> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName).timeout(TIMEOUT);
    }

}

