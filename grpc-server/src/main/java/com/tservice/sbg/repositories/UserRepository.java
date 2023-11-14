package com.tservice.sbg.repositories;

import com.tservice.sbg.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
