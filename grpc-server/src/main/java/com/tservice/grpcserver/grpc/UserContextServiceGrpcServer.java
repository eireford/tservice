package com.tservice.grpcserver.grpc;

import com.tservice.grpcserver.entities.UserContextEntity;
import com.tservice.grpcserver.mappers.UserContextMapper;
import com.tservice.grpcserver.services.UserContextServiceImpl;
import com.tservice.proto.usercontext.CreateProto;
import com.tservice.proto.usercontext.UpdateProto;
import com.tservice.proto.usercontext.UserContextProto;
import com.tservice.proto.usercontext.UserContextServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class UserContextServiceGrpcServer extends UserContextServiceGrpc.UserContextServiceImplBase{

    private final UserContextServiceImpl userContextService;

    @Override
    public void create(CreateProto createProto, StreamObserver<UserContextProto> streamObserver) {
        log.info("Creating UserContext: {}", createProto);
        UserContextEntity userContextEntity = UserContextMapper.createProtoToEntity(createProto);
        try {
            userContextService.save(userContextEntity)
                    .map(UserContextMapper::entityToProto)
                    .doOnSuccess(userContextProto -> {
                        log.info("UserContext created: {}", userContextProto);
                        streamObserver.onNext(userContextProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating UserContext: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error creating UserContext: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<UserContextProto> streamObserver) {
        log.info("Updating UserContext: {}", updateProto);
        UserContextEntity userContextEntity = UserContextMapper.updateProtoToEntity(updateProto);
        try {
            userContextService.update(userContextEntity)
                    .map(UserContextMapper::entityToProto)
                    .doOnSuccess(userContextProto -> {
                        log.info("UserContext updated: {}", userContextProto);
                        streamObserver.onNext(userContextProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating UserContext: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error updating UserContext: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void delete(com.tservice.proto.usercontext.DeleteProto deleteProto, StreamObserver<com.google.protobuf.Empty> streamObserver) {
        log.info("Deleting UserContext: {}", deleteProto);
        UUID uuid = UserContextMapper.deleteProtoToUUID(deleteProto);
        try {
            userContextService.delete(uuid)
                    .doOnSuccess(aVoid -> {
                        log.info("UserContext deleted: {}", deleteProto.getId());
                        streamObserver.onNext(com.google.protobuf.Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting UserContext: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error deleting UserContext: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findById(com.tservice.proto.usercontext.FindByIdProto findByIdProto, StreamObserver<UserContextProto> streamObserver) {
        log.info("Finding UserContext by id: {}", findByIdProto);
        UUID uuid = UserContextMapper.findByIdProtoToUUID(findByIdProto);
        try {
            userContextService.findById(uuid)
                    .map(UserContextMapper::entityToProto)
                    .doOnSuccess(userContextProto -> {
                        log.info("UserContext found: {}", userContextProto);
                        streamObserver.onNext(userContextProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding UserContext: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding UserContext: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findAll(com.tservice.proto.usercontext.FindAllProto findAllProto, StreamObserver<UserContextProto> streamObserver) {
        log.info("Finding all UserContexts");
        try {
            userContextService.findAll()
                    .map(UserContextMapper::entityToProto)
                    .doOnNext(userContextProto -> {
                        log.info("UserContext found: {}", userContextProto);
                        streamObserver.onNext(userContextProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All UserContexts found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding all UserContexts: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding all UserContexts: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByUserId(com.tservice.proto.usercontext.FindByUserIdProto findByUserIdProto, StreamObserver<UserContextProto> streamObserver) {
        log.info("Finding UserContext by userId: {}", findByUserIdProto);
        UUID uuid = UserContextMapper.findByUserIdProtoToUUID(findByUserIdProto);
        try {
            userContextService.findByUserId(uuid)
                    .map(UserContextMapper::entityToProto)
                    .doOnNext(userContextProto -> {
                        log.info("UserContext found: {}", userContextProto);
                        streamObserver.onNext(userContextProto);
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding UserContext: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding UserContext: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByContextId(com.tservice.proto.usercontext.FindByContextIdProto findByContextIdProto, StreamObserver<UserContextProto> streamObserver) {
        log.info("Finding UserContext by contextId: {}", findByContextIdProto);
        UUID uuid = UserContextMapper.findByContextIdProtoToUUID(findByContextIdProto);
        try {
            userContextService.findByContextId(uuid)
                    .map(UserContextMapper::entityToProto)
                    .doOnNext(userContextProto -> {
                        log.info("UserContext found: {}", userContextProto);
                        streamObserver.onNext(userContextProto);
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding UserContext: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding UserContext: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
