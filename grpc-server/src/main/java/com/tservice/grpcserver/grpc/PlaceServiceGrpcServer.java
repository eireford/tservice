package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.PlaceEntity;
import com.tservice.grpcserver.mappers.PlaceMapper;
import com.tservice.grpcserver.services.PlaceServiceImpl;
import com.tservice.proto.place.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class PlaceServiceGrpcServer extends PlaceServiceGrpc.PlaceServiceImplBase {

    private final PlaceServiceImpl placeService;

    @Override
    public void create(CreateProto createProto, StreamObserver<PlaceProto> streamObserver) {
        log.info("Creating place: {}", createProto);
        PlaceEntity placeEntity = PlaceMapper.createProtoToEntity(createProto);
        try {
            placeService.save(placeEntity)
                    .map(PlaceMapper::entityToProto)
                    .doOnSuccess(placeProto -> {
                        log.info("Place created: {}", placeProto);
                        streamObserver.onNext(placeProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating place: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error creating place: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<PlaceProto> streamObserver) {
        log.info("Updating place: {}", updateProto);
        PlaceEntity placeEntity = PlaceMapper.updateProtoToEntity(updateProto);
        try {
            placeService.update(placeEntity)
                    .map(PlaceMapper::entityToProto)
                    .doOnSuccess(placeProto -> {
                        log.info("Place updated: {}", placeProto);
                        streamObserver.onNext(placeProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating place: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error updating place: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver) {
        log.info("Deleting place: {}", deleteProto);
        UUID uuid = PlaceMapper.deleteProtoToUUID(deleteProto);
        try {
            placeService.delete(uuid)
                    .doOnSuccess(aVoid -> {
                        log.info("Place deleted: {}", deleteProto.getId());
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting place: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error deleting place: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findById(FindByIdProto findByIdProto, StreamObserver<PlaceProto> streamObserver) {
        log.info("Finding place: {}", findByIdProto);
        UUID uuid = PlaceMapper.findByIdProtoToUUID(findByIdProto);
        try {
            placeService.findById(uuid)
                    .map(PlaceMapper::entityToProto)
                    .doOnSuccess(placeProto -> {
                        log.info("Place found: {}", placeProto);
                        streamObserver.onNext(placeProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding place: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding place: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<PlaceProto> streamObserver) {
        log.info("Finding all places");
        try {
            placeService.findAll()
                    .map(PlaceMapper::entityToProto)
                    .doOnNext(placeProto -> {
                        log.info("Place found: {}", placeProto);
                        streamObserver.onNext(placeProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All places found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding places: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding places: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findByName(FindByNameProto findByNameProto, StreamObserver<PlaceProto> streamObserver) {
        log.info("Finding place by name: {}", findByNameProto);
        String name = PlaceMapper.findByNameProtoToString(findByNameProto);
        try {
            placeService.findByName(name)
                    .map(PlaceMapper::entityToProto)
                    .doOnNext(placeProto -> {
                        log.info("Place found: {}", placeProto);
                        streamObserver.onNext(placeProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All places found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding places: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding places: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findByValue(FindByValueProto findByValueProto, StreamObserver<PlaceProto> streamObserver) {
        log.info("Finding place by value: {}", findByValueProto);
        String value = PlaceMapper.findByValueProtoToString(findByValueProto);
        try {
            placeService.findByValue(value)
                    .map(PlaceMapper::entityToProto)
                    .doOnNext(placeProto -> {
                        log.info("Place found: {}", placeProto);
                        streamObserver.onNext(placeProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All places found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding places: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding places: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }
}