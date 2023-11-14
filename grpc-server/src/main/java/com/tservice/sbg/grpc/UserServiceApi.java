package com.tservice.sbg.grpc;

import com.tservice.api.CreateUserRequest;
import com.tservice.api.CreateUserResponse;
import com.tservice.api.UserData;
import com.tservice.api.UserServiceGrpc;
import com.tservice.sbg.domain.User;
import com.tservice.sbg.exception.StatusRuntimeExceptionBuilder;
import com.tservice.sbg.services.UserServiceImpl;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@RequiredArgsConstructor
@GrpcService
public class UserServiceApi extends UserServiceGrpc.UserServiceImplBase{

    private final UserServiceImpl userService;


    @Override
    public void createUser(CreateUserRequest createUserRequest, StreamObserver<CreateUserResponse> responseObserver){
        Optional<User> User = userService.saveUser(createUserRequest)
                .blockOptional();
        UserData userData = UserData.newBuilder()
                .setUserId(User.get().getUserId().toString())
                .setUsername(User.get().getUsername())
                .setEmail(User.get().getEmail())
                .build();
        if(User.isPresent()){
            responseObserver.onNext(CreateUserResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("User created successfully")
                    .setUserData(userData)
                    .build());
            responseObserver.onCompleted();
        }
        else {
            responseObserver.onNext(CreateUserResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("User not created")
                    .setUserData(userData)
                    .build());
            // TODO: throw correct exception
            throw StatusRuntimeExceptionBuilder.build(
                    Status.ABORTED.getCode().value(),
                    "user-service",
                    "user not created");
        }

    }
}
