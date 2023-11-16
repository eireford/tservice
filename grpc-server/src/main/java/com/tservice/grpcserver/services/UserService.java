package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.UUID;


public interface UserService {

    Mono<User> saveUser(User user);

    Mono<User> updateUser(User user);

    Mono<Void> deleteUser(UUID userId);

    Mono<User> getUserByUserId(UUID userId);

    Flux<User> getAllUsers();

    Flux<User> findUserByUsername(String username);

    Flux<User> findUserByEmail(String email);

    Flux<User> findUserByFirstName(String email);

    Flux<User> findUserByLastName(String email);
}

