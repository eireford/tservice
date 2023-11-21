package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.UserIdentifierEntity;
import com.tservice.grpcserver.mappers.UserIdentifierMapper;
import com.tservice.grpcserver.services.UserIdentifierService;
import com.tservice.proto.useridentifier.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class UserIdentifierServiceGrpcServer extends UserIdentifierServiceGrpc.UserIdentifierServiceImplBase{

    private final UserIdentifierService userIdentifierService;

    @Override
    public void create(CreateProto createProto, StreamObserver<UserIdentifierProto> streamObserver) {
        log.info("Creating UserIdentifier: {}", createProto);
        UserIdentifierEntity userIdentifierEntity = UserIdentifierMapper.createProtoToEntity(createProto);
        try {
            userIdentifierService.save(userIdentifierEntity)
                    .map(UserIdentifierMapper::entityToProto)
                    .doOnSuccess(userIdentifierProto -> {
                        log.info("UserIdentifier created: {}", userIdentifierProto);
                        streamObserver.onNext(userIdentifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating UserIdentifier: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error creating UserIdentifier: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<UserIdentifierProto> streamObserver) {
        log.info("Updating UserIdentifier: {}", updateProto);
        UserIdentifierEntity userIdentifierEntity = UserIdentifierMapper.updateProtoToEntity(updateProto);
        try {
            userIdentifierService.update(userIdentifierEntity)
                    .map(UserIdentifierMapper::entityToProto)
                    .doOnSuccess(userIdentifierProto -> {
                        log.info("UserIdentifier updated: {}", userIdentifierProto);
                        streamObserver.onNext(userIdentifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating UserIdentifier: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error updating UserIdentifier: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver) {
        log.info("Deleting UserIdentifier: {}", deleteProto);
        UUID uuid = UserIdentifierMapper.deleteProtoToUUID(deleteProto);
        try {
            userIdentifierService.delete(uuid)
                    .doOnSuccess(empty -> {
                        log.info("UserIdentifier deleted: {}", deleteProto.getId());
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting UserIdentifier: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error deleting UserIdentifier: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findById(FindByIdProto findByIdProto, StreamObserver<UserIdentifierProto> streamObserver) {
        log.info("Finding UserIdentifier by id: {}", findByIdProto);
        UUID uuid = UserIdentifierMapper.findByIdProtoToUUID(findByIdProto);
        try {
            userIdentifierService.findById(uuid)
                    .map(UserIdentifierMapper::entityToProto)
                    .doOnSuccess(userIdentifierProto -> {
                        log.info("UserIdentifier found: {}", userIdentifierProto);
                        streamObserver.onNext(userIdentifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding UserIdentifier: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding UserIdentifier: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findByUserId(FindByUserIdProto findByUserIdProto, StreamObserver<UserIdentifierProto> streamObserver) {
        log.info("Finding UserIdentifier by user id: {}", findByUserIdProto);
        UUID uuid = UserIdentifierMapper.findByUserIdProtoToUUID(findByUserIdProto);
        try {
            userIdentifierService.findByUserId(uuid)
                    .map(UserIdentifierMapper::entityToProto)
                    .doOnNext(userIdentifierProto -> {
                        log.info("UserIdentifier found: {}", userIdentifierProto);
                        streamObserver.onNext(userIdentifierProto);
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding UserIdentifier: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding UserIdentifier: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findByIdentifierId(FindByIdentifierIdProto findByIdentifierProto, StreamObserver<UserIdentifierProto> streamObserver) {
        log.info("Finding UserIdentifier by id: {}", findByIdentifierProto);
        UUID uuid = UserIdentifierMapper.findByIdentifierIdProtoToUUID(findByIdentifierProto);
        try {
            userIdentifierService.findByIdentifierId(uuid)
                    .map(UserIdentifierMapper::entityToProto)
                    .doOnNext(userIdentifierProto -> {
                        log.info("UserIdentifier found: {}", userIdentifierProto);
                        streamObserver.onNext(userIdentifierProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All UserIdentifiers found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding UserIdentifier: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding UserIdentifier: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
