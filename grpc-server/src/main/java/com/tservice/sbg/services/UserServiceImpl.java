package com.tservice.sbg.services;

import com.tservice.proto.CreateUserRequest;
import com.tservice.proto.GetUserByUserIdRequest;
import com.tservice.sbg.domain.User;
import com.tservice.sbg.mappers.UserMapper;
import com.tservice.sbg.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final Duration TIMEOUT = Duration.ofSeconds(5);

    private final UserRepository userRepository;

    @Override
    public Mono<User> saveUser(User user) {
        return userRepository.save(user).timeout(TIMEOUT);
    }

    @Override
    public Mono<User> getUserByUserId(UUID userId) {
        return userRepository.findById(userId).timeout(TIMEOUT);
    }

    @Override
    public Mono<User> updateUser(User user) {
        return null;
    }

    @Override
    public Mono<Void> deleteUser(UUID userId) {
        return null;
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll().timeout(TIMEOUT);
    }

    @Override
    public Mono<User> getUserByUsername(@Size(max = 256) String username) {
        return null;
    }

    @Override
    public Mono<User> getUserByEmail(@Email String email) {
        return null;
    }


}
