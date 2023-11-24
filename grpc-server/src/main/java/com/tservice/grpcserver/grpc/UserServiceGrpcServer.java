package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.UserEntity;
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
        log.info("Creating user: {}", createProto);
        UserEntity userEntity = UserMapper.createProtoToEntity(createProto);
        try {
            userService.save(userEntity)
                    .map(UserMapper::entityToProto)
                    .doOnSuccess(userProto -> {
                        log.info("User created: {}", userProto);
                        streamObserver.onNext(userProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating user: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while create user: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<UserProto> streamObserver) {
        log.info("Updating user: {}", updateProto);
        UserEntity userEntity = UserMapper.updateProtoToEntity(updateProto);
        try {
            userService.update(userEntity)
                    .map(UserMapper::entityToProto)
                    .doOnSuccess(userProto -> {
                        log.info("User updated: {}", userProto);
                        streamObserver.onNext(userProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating user: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while update user: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver) {
        log.info("Deleting user: {}", deleteProto);
        UUID id = UserMapper.deleteProtoToUUID(deleteProto);
        try {
            userService.deleteById(id)
                    .doOnSuccess(__ -> {
                        log.info("User deleted: {}", UserMapper.deleteProtoToUUID(deleteProto));
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting user: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while delete user: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getById(GetByIdProto getByIdProto, StreamObserver<UserProto> streamObserver){
        log.info("Finding user by id: {}", getByIdProto);
        UUID id = UserMapper.getByIdProtoToUUID(getByIdProto);
        try {
            userService.getById(id)
                    .map(UserMapper::entityToProto)
                    .doOnSuccess(userProto -> {
                        log.info("User found: {}", userProto);
                        streamObserver.onNext(userProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding user: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while find user: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<UserProto> streamObserver) {
        log.info("Finding all users: {}", findAllProto);
        try {
            userService.findAll()
                    .map(UserMapper::entityToProto)
                    .doOnNext(userProto -> {
                        log.info("User found: {}", userProto);
                        streamObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding all users: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while find all users: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByUsername(FindByUsernameProto findByUsernameProto, StreamObserver<UserProto> streamObserver) {
        log.info("Finding user by username: {}", findByUsernameProto);
        String username = UserMapper.findByUsernameProtoToString(findByUsernameProto);
        try {
            userService.findByUsername(username)
                    .map(UserMapper::entityToProto)
                    .doOnNext(userProto -> {
                        log.info("User found: {}", userProto);
                        streamObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding user by username: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while find user by username: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByEmail(FindByEmailProto findByEmailProto, StreamObserver<UserProto> streamObserver) {
        log.info("Finding user by email: {}", findByEmailProto);
        String email = UserMapper.findByEmailProtoToString(findByEmailProto);
        try {
            userService.findByEmail(email)
                    .map(UserMapper::entityToProto)
                    .doOnNext(userProto -> {
                        log.info("User found: {}", userProto);
                        streamObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding user by email: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while find user by email: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByFirstName(FindByFirstNameProto findByFirstNameProto, StreamObserver<UserProto> streamObserver) {
        log.info("Finding user by first name: {}", findByFirstNameProto);
        String firstName = UserMapper.findByFirstNameProtoToString(findByFirstNameProto);
        try {
            userService.findByFirstName(firstName)
                    .map(UserMapper::entityToProto)
                    .doOnNext(userProto -> {
                        log.info("User found: {}", userProto);
                        streamObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding user by first name: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while find user by first name: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByLastName(FindByLastNameProto findByLastNameProto, StreamObserver<UserProto> streamObserver) {
        log.info("Finding user by last name: {}", findByLastNameProto);
        String lastName = UserMapper.findByLastNameProtoToString(findByLastNameProto);
        try {
            userService.findByLastName(lastName)
                    .map(UserMapper::entityToProto)
                    .doOnNext(userProto -> {
                        log.info("User found: {}", userProto);
                        streamObserver.onNext(userProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All users found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding user by last name: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error while find user by last name: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

}
