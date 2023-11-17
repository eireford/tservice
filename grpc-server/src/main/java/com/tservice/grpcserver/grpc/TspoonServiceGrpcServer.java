package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.mappers.TspoonMapper;
import com.tservice.grpcserver.services.TspoonServiceImpl;
import com.tservice.proto.tspoon.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Sinks;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class TspoonServiceGrpcServer extends TspoonServiceGrpc.TspoonServiceImplBase{

    private final TspoonServiceImpl tspoonService;

    @Override
    public void create(CreateProto createProto, StreamObserver<TspoonProto> streamObserver){
        try{
            tspoonService.save(TspoonMapper.createProtoToEntity(createProto))
                    .map(TspoonMapper::entityToProto)
                    .doOnSuccess(tspoonProto -> {
                        log.info("Tspoon created: {}", tspoonProto);
                        streamObserver.onNext(tspoonProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error creating tspoon: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try{
            tspoonService.update(TspoonMapper.updateProtoToEntity(updateProto))
                    .map(TspoonMapper::entityToProto)
                    .doOnSuccess(tspoonProtoUpdated -> {
                        log.info("Tspoon updated: {}", tspoonProtoUpdated);
                        streamObserver.onNext(tspoonProtoUpdated);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error updating tspoon: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try{
            tspoonService.delete(TspoonMapper.deleteProtoToUUID(deleteProto))
                    .doOnSuccess(__ -> {
                        log.info("Tspoon deleted: {}", TspoonMapper.deleteProtoToUUID(deleteProto));
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error deleting tspoon: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try{
            tspoonService.findById(TspoonMapper.findByIdProtoToUUID(findByIdProto))
                    .map(TspoonMapper::entityToProto)
                    .doOnSuccess(tspoonProtoGet -> {
                        log.info("Tspoon get: {}", tspoonProtoGet);
                        streamObserver.onNext(tspoonProtoGet);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error getting tspoon: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try{
            tspoonService.findByName(TspoonMapper.findByNameProtoToString(findByNameProto))
                    .map(TspoonMapper::entityToProto)
                    .doOnNext(tspoonProtoGet -> {
                        log.info("Tspoon get: {}", tspoonProtoGet);
                        streamObserver.onNext(tspoonProtoGet);
                    })
                    .doOnComplete(() -> {
                        log.info("Tspoon get completed");
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error getting tspoon: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try {
            tspoonService.findByValue(TspoonMapper.findByValueProtoToString(findByValueProto))
                    .map(TspoonMapper::entityToProto)
                    .doOnNext(tspoonProtoGet -> {
                        log.info("Tspoon get: {}", tspoonProtoGet);
                        streamObserver.onNext(tspoonProtoGet);
                    })
                    .doOnComplete(() -> {
                        log.info("Tspoon get completed");
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error getting tspoon: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
