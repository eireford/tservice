package com.tservice.grpcserver.controllers;

import com.tservice.grpcserver.entities.UserEntity;
import com.tservice.grpcserver.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Mono<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return userService.save(userEntity);
    }

    @GetMapping(value = "/{uuid}", produces = "application/json")
    public Mono<UserEntity> getUserById(@PathVariable UUID uuid) {
        return userService.findById(uuid);
    }

    @GetMapping(produces = "application/json")
    public Flux<UserEntity> getAllUsers() {
        return userService.findAll();
    }
}
