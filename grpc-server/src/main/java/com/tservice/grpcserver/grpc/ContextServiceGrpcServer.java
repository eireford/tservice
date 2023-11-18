package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.mappers.ContextMapper;
import com.tservice.grpcserver.services.ContextServiceImpl;
import com.tservice.proto.context.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class ContextServiceGrpcServer extends ContextServiceGrpc.ContextServiceImplBase{

    private final ContextServiceImpl contextService;

    @Override
    public void create(CreateProto createProto, StreamObserver<ContextProto> streamObserver){
        try{
            contextService.save(ContextMapper.createProtoToEntity(createProto))
                    .map(ContextMapper::entityToProto)
                    .doOnSuccess(contextProto -> {
                        log.info("Context created: {}", contextProto);
                        streamObserver.onNext(contextProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error creating context: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error creating context: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<ContextProto> streamObserver){
        try{
            contextService.update(ContextMapper.updateProtoToEntity(updateProto))
                    .map(ContextMapper::entityToProto)
                    .doOnSuccess(contextProto1 -> {
                        log.info("Context updated: {}", contextProto1);
                        streamObserver.onNext(contextProto1);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error updating context: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error updating context: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver){
        try{
            contextService.delete(ContextMapper.deleteProtoToUUID(deleteProto))
                    .doOnSuccess(context -> {
                        log.info("Context deleted: {}", context);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error deleting context: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error deleting context: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<ContextProto> streamObserver){
        try{
            contextService.findAll()
                    .map(ContextMapper::entityToProto)
                    .doOnNext(contextProto -> {
                        log.info("Context found: {}", contextProto);
                        streamObserver.onNext(contextProto);
                    })
                    .doOnComplete(() -> {
                        log.info("Contexts found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding contexts: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding contexts: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findById(FindByIdProto findByIdProto, StreamObserver<ContextProto> streamObserver){
        try{
            contextService.findById(ContextMapper.findByIdProtoToUUID(findByIdProto))
                    .map(ContextMapper::entityToProto)
                    .doOnSuccess(contextProto -> {
                        log.info("Context found: {}", contextProto);
                        streamObserver.onNext(contextProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding context: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding context: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }
}
