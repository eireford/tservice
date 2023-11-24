package com.tservice.grpcserver.grpc;

import com.tservice.grpcserver.entities.TsetPlaceEntity;
import com.tservice.grpcserver.mappers.TsetPlaceMapper;
import com.tservice.grpcserver.services.TsetPlaceServiceImpl;
import com.tservice.proto.tsetplace.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class TsetPlaceServiceGrpcServer extends TsetPlaceServiceGrpc.TsetPlaceServiceImplBase{

    private final TsetPlaceServiceImpl tsetPlaceService;

    @Override
    public void create(CreateProto createProto, StreamObserver<TsetPlaceProto> streamObserver) {
        log.info("Creating TsetPlace: {}", createProto);
        TsetPlaceEntity tsetPlaceEntity = TsetPlaceMapper.createProtoToEntity(createProto);
        try {
            tsetPlaceService.save(tsetPlaceEntity)
                    .map(TsetPlaceMapper::entityToProto)
                    .doOnSuccess(tsetPlaceProto -> {
                        log.info("TsetPlace created: {}", tsetPlaceProto);
                        streamObserver.onNext(tsetPlaceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating TsetPlace: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error creating TsetPlace: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<TsetPlaceProto> streamObserver) {
        log.info("Updating TsetPlace: {}", updateProto);
        TsetPlaceEntity tsetPlaceEntity = TsetPlaceMapper.updateProtoToEntity(updateProto);
        try {
            tsetPlaceService.update(tsetPlaceEntity)
                    .map(TsetPlaceMapper::entityToProto)
                    .doOnSuccess(tsetPlaceProto -> {
                        log.info("TsetPlace updated: {}", tsetPlaceProto);
                        streamObserver.onNext(tsetPlaceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating TsetPlace: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error updating TsetPlace: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<com.google.protobuf.Empty> streamObserver) {
        log.info("Deleting TsetPlace: {}", deleteProto);
        UUID id = TsetPlaceMapper.deleteProtoToUUID(deleteProto);
        try {
            tsetPlaceService.deleteById(id)
                    .doOnSuccess(__ -> {
                        log.info("TsetPlace deleted: {}", deleteProto.getId());
                        streamObserver.onNext(com.google.protobuf.Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting TsetPlace: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error deleting TsetPlace: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getById(GetByIdProto getByIdProto, StreamObserver<TsetPlaceProto> streamObserver) {
        log.info("Finding TsetPlace by id: {}", getByIdProto);
        UUID id = TsetPlaceMapper.getByIdProtoToUUID(getByIdProto);
        try {
            tsetPlaceService.getById(id)
                    .map(TsetPlaceMapper::entityToProto)
                    .doOnSuccess(tsetPlaceProto -> {
                        log.info("TsetPlace found: {}", tsetPlaceProto);
                        streamObserver.onNext(tsetPlaceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding TsetPlace: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding TsetPlace: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<TsetPlaceProto> streamObserver) {
        log.info("Finding all TsetPlace: {}", findAllProto);
        try {
            tsetPlaceService.findAll()
                    .map(TsetPlaceMapper::entityToProto)
                    .doOnNext(tsetPlaceProto -> {
                        log.info("TsetPlace found: {}", tsetPlaceProto);
                        streamObserver.onNext(tsetPlaceProto);
                    })
                    .doOnNext(tsetPlaceProto -> {
                        log.info("All TsetPlace found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding all TsetPlace: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding all TsetPlace: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void findByTsetId(FindByTsetIdProto findByTsetIdProto, StreamObserver<TsetPlaceProto> streamObserver){
        log.info("Finding TsetPlace by Tset id: {}", findByTsetIdProto);
        UUID id = TsetPlaceMapper.findByTsetIdProtoToUUID(findByTsetIdProto);
        try {
            tsetPlaceService.findByTsetId(id)
                    .map(TsetPlaceMapper::entityToProto)
                    .doOnNext(tsetPlaceProto -> {
                        log.info("TsetPlace found: {}", tsetPlaceProto);
                        streamObserver.onNext(tsetPlaceProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All TsetPlace found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding TsetPlace: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding TsetPlace: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    public void findByPlaceId(FindByPlaceIdProto findByPlaceIdProto, StreamObserver<TsetPlaceProto> streamObserver){
        log.info("Finding TsetPlace by Place id: {}", findByPlaceIdProto);
        UUID id = TsetPlaceMapper.findByPlaceIdProtoToUUID(findByPlaceIdProto);
        try {
            tsetPlaceService.findByPlaceId(id)
                    .map(TsetPlaceMapper::entityToProto)
                    .doOnNext(tsetPlaceProto -> {
                        log.info("TsetPlace found: {}", tsetPlaceProto);
                        streamObserver.onNext(tsetPlaceProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All TsetPlace found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding TsetPlace: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding TsetPlace: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        }
    }
}
