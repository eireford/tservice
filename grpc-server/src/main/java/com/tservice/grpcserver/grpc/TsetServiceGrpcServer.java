package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.TsetEntity;
import com.tservice.grpcserver.mappers.TsetMapper;
import com.tservice.grpcserver.services.TsetServiceImpl;
import com.tservice.proto.tset.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class TsetServiceGrpcServer extends TsetServiceGrpc.TsetServiceImplBase{

    private final TsetServiceImpl tsetService;

    @Override
    public void create(CreateProto createProto, StreamObserver<TsetProto> streamObserver) {
        log.info("Creating Tset: {}", createProto);
        TsetEntity tsetEntity = TsetMapper.createProtoToEntity(createProto);
        try {
            tsetService.save(tsetEntity)
                    .map(TsetMapper::entityToProto)
                    .doOnSuccess(tsetProto -> {
                        log.info("Tset created: {}", tsetProto);
                        streamObserver.onNext(tsetProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating Tset: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error creating Tset: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<TsetProto> streamObserver) {
        log.info("Updating Tset: {}", updateProto);
        TsetEntity tsetEntity = TsetMapper.updateProtoToEntity(updateProto);
        try {
            tsetService.update(tsetEntity)
                    .map(TsetMapper::entityToProto)
                    .doOnSuccess(tsetProto -> {
                        log.info("Tset updated: {}", tsetProto);
                        streamObserver.onNext(tsetProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating Tset: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error updating Tset: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver) {
        log.info("Deleting Tset: {}", deleteProto);
        UUID id = TsetMapper.deleteProtoToUUID(deleteProto);
        try {
            tsetService.deleteById(id)
                    .doOnSuccess(__ -> {
                        log.info("Tset deleted: {}", deleteProto);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting Tset: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error deleting Tset: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getById(GetByIdProto getByIdProto, StreamObserver<TsetProto> streamObserver) {
        log.info("Finding Tset by id: {}", getByIdProto);
        UUID id = TsetMapper.getByIdProtoToUUID(getByIdProto);
        try {
            tsetService.getById(id)
                    .map(TsetMapper::entityToProto)
                    .doOnSuccess(tsetProto -> {
                        log.info("Tset found: {}", tsetProto);
                        streamObserver.onNext(tsetProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding Tset: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding Tset: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<TsetProto> streamObserver) {
        log.info("Finding all Tsets");
        try {
            tsetService.findAll()
                    .map(TsetMapper::entityToProto)
                    .doOnNext(tsetProto -> {
                        log.info("Tset found: {}", tsetProto);
                        streamObserver.onNext(tsetProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All Tsets found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding all Tsets: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding all Tsets: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByName(FindByNameProto findByNameProto, StreamObserver<TsetProto> streamObserver) {
        log.info("Finding Tset by name: {}", findByNameProto);
        String name = TsetMapper.findByNameProtoToString(findByNameProto);
        try {
            tsetService.findByName(name)
                    .map(TsetMapper::entityToProto)
                    .doOnNext(tsetEntity -> {
                        log.info("Tset found: {}", tsetEntity);
                        streamObserver.onNext(tsetEntity);
                    })
                    .doOnComplete(() -> {
                        log.info("All Tsets found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding Tset: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding Tset: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByValue(FindByValueProto findByValueProto, StreamObserver<TsetProto> streamObserver) {
        log.info("Finding Tset by value: {}", findByValueProto);
        String value = TsetMapper.findByValueProtoToString(findByValueProto);
        try {
            tsetService.findByValue(value)
                    .map(TsetMapper::entityToProto)
                    .doOnNext(tsetProto -> {
                        log.info("Tset found: {}", tsetProto);
                        streamObserver.onNext(tsetProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All Tsets found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding Tset: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding Tset: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

}
