package com.tservice.sbg.services;

import com.tservice.proto.CreateUserRequest;
import com.tservice.proto.GetUserByUserIdRequest;
import com.tservice.sbg.domain.User;
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

    Mono<User> getUserByUsername(@Size(max = 256) String username);

    Mono<User> getUserByEmail(@Email String email);
}

