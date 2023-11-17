package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.mappers.UserMapper;
import com.tservice.grpcserver.services.UserServiceImpl;
import com.tservice.proto.user.*;
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
    public void create(CreateProto createProto, StreamObserver<UserProto> streamObserver) {
        try {
            userService.save(UserMapper.createProtoToEntity(createProto))
                    .map(UserMapper::entityToProto)
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
    public void update(UpdateProto updateProto, StreamObserver<UserProto> streamObserver) {
        try {
            userService.update(UserMapper.updateProtoToEntity(updateProto))
                    .map(UserMapper::entityToProto)
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
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver) {
        try {
            UUID userId = UUID.fromString(deleteProto.getId());

            userService.delete(userId)
                    .doOnSuccess(__ -> {
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
            log.error("Invalid UUID format in deleteProto.getId(): {}", deleteProto.getId());
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
    public void findById(FindByIdProto findByIdProto, StreamObserver<UserProto> responseObserver) {
        try {
            UUID userId = UUID.fromString(findByIdProto.getId());

            userService.findById(userId)
                    .map(UserMapper::entityToProto)
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
            log.error("Invalid UUID format in getByIdProto.getId(): {}", findByIdProto.getId());
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
    public void findAll(FindAllProto findAllProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findAll()
                    .map(UserMapper::entityToProto)
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
    public void findByUsername(FindByUsernameProto findByUsernameProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findByUsername(findByUsernameProto.getUsername())
                    .map(UserMapper::entityToProto)
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
    public void findByEmail(FindByEmailProto findByEmailProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findByEmail(findByEmailProto.getEmail())
                    .map(UserMapper::entityToProto)
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
    public void findByFirstName(FindByFirstNameProto findByFirstNameProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findByFirstName(findByFirstNameProto.getFirstName())
                    .map(UserMapper::entityToProto)
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
    public void findByLastName(FindByLastNameProto findByLastNameProto, StreamObserver<UserProto> responseObserver) {
        try {
            userService.findByLastName(findByLastNameProto.getLastName())
                    .map(UserMapper::entityToProto)
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
