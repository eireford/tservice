package com.tservice.grpcserver.grpc;

import com.tservice.proto.CreateUserRequest;
import com.tservice.proto.CreateUserResponse;
import com.tservice.proto.UserData;
import com.tservice.proto.GetUserByUserIdRequest;
import com.tservice.proto.GetAllUsersRequestStream;
import com.tservice.proto.GetAllUsersResponseStream;
import com.tservice.grpcserver.mappers.UserMapper;
import com.tservice.grpcserver.services.UserServiceImpl;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@GrpcService
public class UserServiceGrpc extends com.tservice.proto.UserServiceGrpc.UserServiceImplBase {

    private final UserServiceImpl userService;

    @Override
    public void createUser(CreateUserRequest createUserRequest,
                           @NotNull StreamObserver<CreateUserResponse> responseObserver) {
        userService.saveUser(UserMapper.requestToUser(createUserRequest))
                .map(user -> {
                    UserData userData = UserData.newBuilder()
                            .setUserId(user.getUserId().toString())
                            .setUsername(user.getUsername())
                            .setEmail(user.getEmail())
                            .setFirstName(user.getFirstName())
                            .setLastName(user.getLastName())
                            .build();
                    return CreateUserResponse.newBuilder()
                            .setSuccess(true)
                            .setMessage("User created successfully")
                            .setUserData(userData)
                            .build();
                })
                .defaultIfEmpty(CreateUserResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("User not created")
                        .setUserData(UserData.newBuilder().build())
                        .build())
                .subscribe(responseObserver::onNext, responseObserver::onError, responseObserver::onCompleted);
    }

    @Override
    public void getUserByUserId(GetUserByUserIdRequest getUserByUserIdRequest,
                                StreamObserver<UserData> responseObserver) {
        userService.getUserByUserId(UserMapper.requestToUserId(getUserByUserIdRequest))
                .map(user -> UserData.newBuilder()
                        .setUserId(user.getUserId().toString())
                        .setUsername(user.getUsername())
                        .setEmail(user.getEmail())
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .build())
                .defaultIfEmpty(UserData.newBuilder().build())
                .subscribe(responseObserver::onNext, responseObserver::onError, responseObserver::onCompleted);
    }

    @Override
    public void getAllUsersStream(GetAllUsersRequestStream request,
                                  StreamObserver<GetAllUsersResponseStream> responseObserver) {
        userService.getAllUsers()
                .map(user -> UserData.newBuilder()
                        .setUserId(user.getUserId().toString())
                        .setUsername(user.getUsername())
                        .setEmail(user.getEmail())
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .build())
                .collectList()
                .map(userList -> GetAllUsersResponseStream.newBuilder()
                        .addAllUserData(userList)
                        .build())
                .subscribe(responseObserver::onNext, responseObserver::onError, responseObserver::onCompleted);
    }
}