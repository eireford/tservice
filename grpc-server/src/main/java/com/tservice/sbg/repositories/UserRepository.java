package com.tservice.sbg.repositories;

import com.tservice.sbg.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface UserRepository
//        extends ReactiveSortingRepository<User, UUID>
{
//    Flux<User> getAll(Pageable pageable);
//
//    Flux<User> findByUsername(String username, Pageable pageable);
//
//    Flux<User> getById(UUID id);
}
