package com.tservice.grpcserver.repositories;

import com.tservice.grpcserver.entities.User;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserRepository extends ReactiveSortingRepository<User, UUID>
//        extends ReactiveSortingRepository<User, UUID>
{
//    @Override
//    Mono<User> findById(UUID uuid);
//    Flux<User> getAll(Pageable pageable);
//
//    Flux<User> findByUsername(String username, Pageable pageable);
//
//    Flux<User> getById(UUID id);
}
