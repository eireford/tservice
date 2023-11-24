package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.IdentifierEntity;
import com.tservice.grpcserver.mappers.IdentifierMapper;
import com.tservice.grpcserver.services.IdentifierServiceImpl;
import com.tservice.proto.identifier.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class IdentifierServiceGrpcServer extends IdentifierServiceGrpc.IdentifierServiceImplBase{

    private final IdentifierServiceImpl identifierService;

    @Override
    public void create(CreateProto createProto, StreamObserver<IdentifierProto> streamObserver){
        log.info("Creating identifier: {}", createProto);
        IdentifierEntity identifierEntity = IdentifierMapper.createProtoToEntity(createProto);
        try{
            identifierService.save(identifierEntity)
                    .map(IdentifierMapper::entityToProto)
                    .doOnSuccess(identifierProto -> {
                        log.info("Identifier created: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating identifier: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error creating identifier: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<IdentifierProto> streamObserver){
        log.info("Updating identifier: {}", updateProto);
        IdentifierEntity identifierEntity = IdentifierMapper.updateProtoToEntity(updateProto);
        try{
            identifierService.update(identifierEntity)
                    .map(IdentifierMapper::entityToProto)
                    .doOnSuccess(identifierProto -> {
                        log.info("Identifier updated: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating identifier: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error updating identifier: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver){
        log.info("Deleting identifier: {}", deleteProto);
        UUID id = IdentifierMapper.deleteProtoToUUID(deleteProto);
        try{
            identifierService.deleteById(id)
                    .doOnSuccess(identifierProto -> {
                        log.info("Identifier deleted: {}", identifierProto);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting identifier: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error deleting identifier: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void getById(GetByIdProto getByIdProto, StreamObserver<IdentifierProto> streamObserver){
        log.info("Finding identifier: {}", getByIdProto);
        UUID id = IdentifierMapper.getByIdProtoToUUID(getByIdProto);
        try{
            identifierService.getById(id)
                    .map(IdentifierMapper::entityToProto)
                    .doOnSuccess(identifierProto -> {
                        log.info("Identifier found: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding identifier: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding identifier: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<IdentifierProto> streamObserver){
        log.info("Finding all identifiers");
        try {
            identifierService.findAll()
                    .map(IdentifierMapper::entityToProto)
                    .doOnNext(identifierProto -> {
                        log.info("Identifier found: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All identifiers found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding identifiers: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding identifiers: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findByName(FindByNameProto findByNameProto, StreamObserver<IdentifierProto> streamObserver){
        log.info("Finding identifier by name: {}", findByNameProto);
        String name = IdentifierMapper.findByNameProtoToString(findByNameProto);
        try{
            identifierService.findByName(name)
                    .map(IdentifierMapper::entityToProto)
                    .doOnNext(identifierProto -> {
                        log.info("Identifier found: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All identifiers found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding identifiers: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding identifiers: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findByValue(FindByValueProto findByValueProto, StreamObserver<IdentifierProto> streamObserver){
        log.info("Finding identifier by value: {}", findByValueProto);
        String value = IdentifierMapper.findByValueProtoToString(findByValueProto);
        try{
            identifierService.findByValue(value)
                    .map(IdentifierMapper::entityToProto)
                    .doOnNext(identifierProto -> {
                        log.info("Identifier found: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All identifiers found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding identifiers: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding identifiers: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

}
