package com.tservice.sbg.services;

import com.tservice.api.CreateUserRequest;
import com.tservice.api.GetUserByUserIdRequest;
import com.tservice.sbg.domain.User;
import com.tservice.sbg.mappers.UserMapper;
import com.tservice.sbg.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final UserRepository userRepository;
    @Override
    public Mono<User> saveUser(CreateUserRequest createUserRequest) {
        return userRepository.save(UserMapper.requestToUser(createUserRequest));
    }

    @Override
    public Mono<User> getUserByUserId(GetUserByUserIdRequest getUserByUserIdRequest) {
        return userRepository.findById(UUID.fromString(getUserByUserIdRequest.getUserId()));
    }
}
