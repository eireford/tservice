package com.tservice.grpcserver.controllers;

import com.tservice.grpcserver.entities.User;
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
    public Mono<User> createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/{uuid}", produces = "application/json")
    public Mono<User> getUserById(@PathVariable UUID uuid) {
        return userService.getUserByUserId(uuid);
    }

    @GetMapping(produces = "application/json")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
