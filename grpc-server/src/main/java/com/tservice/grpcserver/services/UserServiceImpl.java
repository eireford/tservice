package com.tservice.grpcserver.services;

import com.tservice.grpcserver.entities.User;
import com.tservice.grpcserver.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

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
        // Assuming you have a proper implementation for updating a user
        return userRepository.save(user).timeout(TIMEOUT);
    }

    @Override
    public Mono<Void> deleteUser(UUID userId) {
        return userRepository.deleteById(userId).timeout(TIMEOUT);
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll().timeout(TIMEOUT);
    }

    @Override
    public Flux<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username).timeout(TIMEOUT);
    }

    @Override
    public Flux<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email).timeout(TIMEOUT);
    }

    @Override
    public Flux<User> findUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName).timeout(TIMEOUT);
    }

    @Override
    public Flux<User> findUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName).timeout(TIMEOUT);
    }

}

