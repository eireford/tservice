package com.tservice.sbg.controllers;

import com.tservice.sbg.domain.User;
import com.tservice.sbg.services.UserService;
import com.tservice.api.GetUserByUserIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public Mono<User> getUserById(GetUserByUserIdRequest getUserByUserIdRequest) {
        return userService.getUserByUserId(getUserByUserIdRequest);
    }
}
