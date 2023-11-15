package com.tservice.httpserver.services;

import com.tservice.httpserver.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
public class UserService {

    @GrpcClient("grpc-server-service")
    private com.tservice.proto.UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public Mono<UserDto> saveUser(UserDto user) {
        return null;
    }

    public Mono<UserDto> getUserByUserId(UUID uuid) {
        return null;
    }

    public Flux<UserDto> getAllUsers() {
        return null;
    }

}
