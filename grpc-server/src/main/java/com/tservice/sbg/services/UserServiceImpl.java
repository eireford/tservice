package com.tservice.sbg.services;

import com.tservice.api.CreateUserRequest;
import com.tservice.sbg.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class UserServiceImpl implements UserService{
    @Override
    public Mono<User> createUser(CreateUserRequest createUserRequest) {
        return null;
    }
}
