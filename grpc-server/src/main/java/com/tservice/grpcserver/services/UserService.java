package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


public interface UserService {

    Mono<UserEntity> save(UserEntity user);

    Mono<UserEntity> update(UserEntity user);

    Mono<Void> delete(UUID uuid);

    Mono<UserEntity> findById(UUID uuid);

    Flux<UserEntity> findAll();

    Flux<UserEntity> findByUsername(String username);

    Flux<UserEntity> findByEmail(String email);

    Flux<UserEntity> findByFirstName(String firstName);

    Flux<UserEntity> findByLastName(String lastName);
}

