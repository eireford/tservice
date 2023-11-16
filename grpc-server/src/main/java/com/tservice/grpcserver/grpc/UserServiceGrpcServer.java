package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.mappers.UserMapper;
import com.tservice.grpcserver.services.UserServiceImpl;
import com.tservice.proto.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class UserServiceGrpcServer extends UserServiceGrpc.UserServiceImplBase {

    private final UserServiceImpl userService;



    @Override
    public void createUser(CreateUserProto createUserProto, StreamObserver<UserProto> streamObserver) {
        try {
            userService.saveUser(UserMapper.createUserProtoToUser(createUserProto))
                    .map(UserMapper::UserEntityToUserProto)
                    .doOnSuccess(userProto -> {
                        log.info("User created successfully");
                        streamObserver.onNext(userProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while creating user: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while processing create user request: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void updateUser(UpdateUserProto updateUserProto, StreamObserver<UserProto> streamObserver) {
        try {
            userService.updateUser(UserMapper.updateUserProtoToUserEntity(updateUserProto))
                    .map(UserMapper::UserEntityToUserProto)
                    .doOnSuccess(userProto -> {
                        log.info("User updated successfully");
                        streamObserver.onNext(userProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while updating user: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while processing update user request: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void deleteUser(DeleteUserProto deleteUserProto, StreamObserver<Empty> streamObserver) {
        try {
            UUID userId = UUID.fromString(deleteUserProto.getUserId());

            userService.deleteUser(userId)
                    .doOnSuccess(user -> {
                        log.info("User with ID {} deleted successfully", userId);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while deleting user with ID {}: {}", userId, error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format in deleteUserProto.getUserId(): {}", deleteUserProto.getUserId());
            streamObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid UUID format")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        } catch (Exception e) {
            log.error("Error while processing delete user request: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }


    @Override
    public void getUserByUserId(GetUserByUserIdProto getUserByUserIdProto, StreamObserver<UserProto> responseObserver) {
        try {
            UUID userId = UUID.fromString(getUserByUserIdProto.getUserId());

            userService.getUserByUserId(userId)
                    .map(UserMapper::UserEntityToUserProto)
                    .doOnSuccess(userProto -> {
                        log.info("User with ID {} found successfully", userId);
                        responseObserver.onNext(userProto);
                        responseObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while getting user with ID {}: {}", userId, error.getMessage());
                        responseObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format in getUserByUserIdProto.getUserId(): {}", getUserByUserIdProto.getUserId());
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid UUID format")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        } catch (Exception e) {
            log.error("Error while processing get user by user id request: {}", e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getAllUsers(GetAllUsersProto getAllUsersProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.getAllUsers()
                    .map(UserMapper::UserEntityToUserProto)
                    .doOnNext(userProto -> {
                        log.info("User found successfully");
                        responseObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found successfully");
                        responseObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while getting all users: {}", error.getMessage());
                        responseObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while processing get all users request: {}", e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findUsersByUsername(FindUsersByUsernameProto findUsersByUsernameProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findUserByUsername(findUsersByUsernameProto.getUsername())
                    .map(UserMapper::UserEntityToUserProto)
                    .doOnNext(userProto -> {
                        log.info("User found successfully");
                        responseObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found successfully");
                        responseObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while getting all users: {}", error.getMessage());
                        responseObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while processing find users by username request: {}", e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findUsersByEmail(FindUsersByEmailProto findUsersByEmailProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findUserByEmail(findUsersByEmailProto.getEmail())
                    .map(UserMapper::UserEntityToUserProto)
                    .doOnNext(userProto -> {
                        log.info("User found successfully");
                        responseObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found successfully");
                        responseObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while getting all users: {}", error.getMessage());
                        responseObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while processing find users by email request: {}", e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findUsersByFirstName(FindUsersByFirstNameProto findUsersByFirstNameProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findUserByFirstName(findUsersByFirstNameProto.getFirstName())
                    .map(UserMapper::UserEntityToUserProto)
                    .doOnNext(userProto -> {
                        log.info("User found successfully");
                        responseObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found successfully");
                        responseObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while getting all users: {}", error.getMessage());
                        responseObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while processing find users by first name request: {}", e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findUsersByLastName(FindUsersByLastNameProto findUsersByLastNameProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findUserByLastName(findUsersByLastNameProto.getLastName())
                    .map(UserMapper::UserEntityToUserProto)
                    .doOnNext(userProto -> {
                        log.info("User found successfully");
                        responseObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found successfully");
                        responseObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error while getting all users: {}", error.getMessage());
                        responseObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while processing find users by last name request: {}", e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

}
