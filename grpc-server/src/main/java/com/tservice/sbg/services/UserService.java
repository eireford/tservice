package com.tservice.sbg.services;

import com.tservice.api.CreateUserRequest;
import com.tservice.sbg.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> createUser(CreateUserRequest createUserRequest);

//    Mono<User> createUser(CreateUserRequest createUserRequest);
//
//    Mono<User> getUserByUserId(UUID id);
//
//    Flux<User> findUsersByUsername(FindUsersByUsernameRequestStream findUsersByUsernameRequestStream);
//
//    Flux<User> findUsersByUsername(FindUsersByUsernameRequestStream findUsersByUsernameRequestStream);
//
//    Mono<Page<User>> findUsersByUsernamePageableRequestDto(FindByUsernameRequestDto findByUsernameRequestDto);
//    Flux<User> findByEmail(FindByEmailRequestDto findByEmailRequestDto);
//
//    Mono<Page<User>> findByUsernameAsPage(FindByUsernameRequestDto findByUsernameRequestDto);
//
//    Mono<Page<User>> findByEmailAsPage(FindByEmailRequestDto findByEmailRequestDto);
//
//    Mono<User> getUserByUserId(GetUserByUserIdRequest getUserByUserIdRequest);
//
//    Mono<User> getUserByUserId(GetUserByUserIdRequest getUserByUserIdRequest);
}

