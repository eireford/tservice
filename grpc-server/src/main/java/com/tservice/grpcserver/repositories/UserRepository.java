package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Repository
public interface UserRepository extends ReactiveSortingRepository<UserEntity, UUID> {

    Mono<UserEntity> update(UserEntity user);

    Mono<Void> delete(UUID uuid);

    Flux<UserEntity> findByUsername(String username);

    Flux<UserEntity> findByEmail(String email);

    Flux<UserEntity> findByFirstName(String firstName);

    Flux<UserEntity> findByLastName(String lastName);


}
