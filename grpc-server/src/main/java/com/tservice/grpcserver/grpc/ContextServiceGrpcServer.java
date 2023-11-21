package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.ContextEntity;
import com.tservice.grpcserver.mappers.ContextMapper;
import com.tservice.grpcserver.services.ContextServiceImpl;
import com.tservice.proto.context.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class ContextServiceGrpcServer extends ContextServiceGrpc.ContextServiceImplBase{

    private final ContextServiceImpl contextService;

    @Override
    public void create(CreateProto createProto, StreamObserver<ContextProto> streamObserver){
        log.info("Creating context: {}", createProto);
        ContextEntity contextEntity = ContextMapper.createProtoToEntity(createProto);
        try{
            contextService.save(contextEntity)
                    .map(ContextMapper::entityToProto)
                    .doOnSuccess(contextProto -> {
                        log.info("Context created: {}", contextProto);
                        streamObserver.onNext(contextProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating context: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
        log.info("Updating context: {}", updateProto);
        ContextEntity contextEntity = ContextMapper.updateProtoToEntity(updateProto);
        try{
            contextService.update(contextEntity)
                    .map(ContextMapper::entityToProto)
                    .doOnSuccess(contextProto -> {
                        log.info("Context updated: {}", contextProto);
                        streamObserver.onNext(contextProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating context: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
        log.info("Deleting context: {}", deleteProto);
        UUID uuid = ContextMapper.deleteProtoToUUID(deleteProto);
        try{
            contextService.delete(uuid)
                    .doOnSuccess(context -> {
                        log.info("Context deleted: {}", context);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting context: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
    public void findById(FindByIdProto findByIdProto, StreamObserver<ContextProto> streamObserver){
        log.info("Finding context: {}", findByIdProto);
        UUID uuid = ContextMapper.findByIdProtoToUUID(findByIdProto);
        try{
            contextService.findById(uuid)
                    .map(ContextMapper::entityToProto)
                    .doOnSuccess(contextProto -> {
                        log.info("Context found: {}", contextProto);
                        streamObserver.onNext(contextProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding context: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<ContextProto> streamObserver){
        log.info("Finding contexts: {}", findAllProto);
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
                    .doOnError(throwable -> {
                        log.error("Error finding contexts: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
    public void findByName(FindByNameProto findByNameProto, StreamObserver<ContextProto> streamObserver){
        log.info("Finding contexts: {}", findByNameProto);
        String name = ContextMapper.findByNameProtoToString(findByNameProto);
        try{
            contextService.findByName(name)
                    .map(ContextMapper::entityToProto)
                    .doOnNext(contextProto -> {
                        log.info("Context found: {}", contextProto);
                        streamObserver.onNext(contextProto);
                    })
                    .doOnComplete(() -> {
                        log.info("Contexts found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding contexts: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
    public void findByValue(FindByValueProto findByValueProto, StreamObserver<ContextProto> streamObserver){
        log.info("Finding contexts: {}", findByValueProto);
        String value = ContextMapper.findByValueProtoToString(findByValueProto);
        try{
            contextService.findByValue(value)
                    .map(ContextMapper::entityToProto)
                    .doOnNext(contextProto -> {
                        log.info("Context found: {}", contextProto);
                        streamObserver.onNext(contextProto);
                    })
                    .doOnComplete(() -> {
                        log.info("Contexts found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding contexts: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
}
