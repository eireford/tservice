package com.tservice.httpserver.controller;

import com.tservice.httpserver.dto.UserDto;
import com.tservice.httpserver.services.UserService;
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
    public Mono<UserDto> createUser(@RequestBody UserDto user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/{uuid}", produces = "application/json")
    public Mono<UserDto> getUserById(@PathVariable UUID uuid) {
        return userService.getUserByUserId(uuid);
    }

    @GetMapping(produces = "application/json")
    public Flux<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
