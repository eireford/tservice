package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.TspoonEntity;
import com.tservice.grpcserver.mappers.TspoonMapper;
import com.tservice.grpcserver.services.TspoonServiceImpl;
import com.tservice.proto.tspoon.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class TspoonServiceGrpcServer extends TspoonServiceGrpc.TspoonServiceImplBase{

    private final TspoonServiceImpl tspoonService;

    @Override
    public void create(CreateProto createProto, StreamObserver<TspoonProto> streamObserver){
        log.info("Tspoon create: {}", createProto);
        TspoonEntity tspoonEntity = TspoonMapper.createProtoToEntity(createProto);
        try{
            tspoonService.save(tspoonEntity)
                    .map(TspoonMapper::entityToProto)
                    .doOnSuccess(tspoonProto -> {
                        log.info("Tspoon created: {}", tspoonProto);
                        streamObserver.onNext(tspoonProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating tspoon: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error creating tspoon: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<TspoonProto> streamObserver){
        log.info("Tspoon update: {}", updateProto);
        TspoonEntity tspoonEntity = TspoonMapper.updateProtoToEntity(updateProto);
        try{
            tspoonService.update(tspoonEntity)
                    .map(TspoonMapper::entityToProto)
                    .doOnSuccess(tspoonProto -> {
                        log.info("Tspoon updated: {}", tspoonProto);
                        streamObserver.onNext(tspoonProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating tspoon: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error updating tspoon: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver){
        log.info("Tspoon delete: {}", deleteProto);
        UUID uuid = TspoonMapper.deleteProtoToUUID(deleteProto);
        try{
            tspoonService.delete(uuid)
                    .doOnSuccess(__ -> {
                        log.info("Tspoon deleted: {}", TspoonMapper.deleteProtoToUUID(deleteProto));
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting tspoon: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error deleting tspoon: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findById(FindByIdProto findByIdProto, StreamObserver<TspoonProto> streamObserver){
        log.info("Tspoon get: {}", findByIdProto);
        UUID uuid = TspoonMapper.findByIdProtoToUUID(findByIdProto);
        try{
            tspoonService.findById(uuid)
                    .doOnSuccess(tspoonEntity -> {
                        log.info("Tspoon get: {}", tspoonEntity);
                        streamObserver.onNext(TspoonMapper.entityToProto(tspoonEntity));
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error getting tspoon: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error getting tspoon: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<TspoonProto> streamObserver){
        log.info("Tspoon get all");
        try{
            tspoonService.findAll()
                    .map(TspoonMapper::entityToProto)
                    .doOnNext(tspoonProto -> {
                        log.info("Tspoon get: {}", tspoonProto);
                        streamObserver.onNext(tspoonProto);
                    })
                    .doOnComplete(() -> {
                        log.info("Tspoon get completed");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error getting tspoon: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error getting tspoon: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findByName(FindByNameProto findByNameProto, StreamObserver<TspoonProto> streamObserver){
        log.info("Tspoon get: {}", findByNameProto);
        String name = TspoonMapper.findByNameProtoToString(findByNameProto);
        try{
            tspoonService.findByName(name)
                    .map(TspoonMapper::entityToProto)
                    .doOnNext(tspoonProto -> {
                        log.info("Tspoon get: {}", tspoonProto);
                        streamObserver.onNext(tspoonProto);
                    })
                    .doOnComplete(() -> {
                        log.info("Tspoon get completed");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error getting tspoon: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error getting tspoon: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findByValue(FindByValueProto findByValueProto, StreamObserver<TspoonProto> streamObserver){
        log.info("Tspoon get: {}", findByValueProto);
        String value = TspoonMapper.findByValueProtoToString(findByValueProto);
        try {
            tspoonService.findByValue(value)
                    .map(TspoonMapper::entityToProto)
                    .doOnNext(tspoonProto -> {
                        log.info("Tspoon get: {}", tspoonProto);
                        streamObserver.onNext(tspoonProto);
                    })
                    .doOnComplete(() -> {
                        log.info("Tspoon get completed");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error getting tspoon: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error getting tspoon: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }
}
