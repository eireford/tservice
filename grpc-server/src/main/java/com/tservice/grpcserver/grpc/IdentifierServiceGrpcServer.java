package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.mappers.IdentifierMapper;
import com.tservice.grpcserver.services.IdentifierServiceImpl;
import com.tservice.proto.identifier.*;
import com.tservice.proto.tsetplace.FindByTsetIdProto;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class IdentifierServiceGrpcServer extends IdentifierServiceGrpc.IdentifierServiceImplBase{

    private final IdentifierServiceImpl identifierService;

    @Override
    public void create(CreateProto createProto, StreamObserver<IdentifierProto> streamObserver){
        try{
            identifierService.save(IdentifierMapper.createProtoToEntity(createProto))
                    .map(IdentifierMapper::entityToProto)
                    .doOnSuccess(identifierProto -> {
                        log.info("Identifier created: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error creating identifier: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try{
            identifierService.update(IdentifierMapper.updateProtoToEntity(updateProto))
                    .map(IdentifierMapper::entityToProto)
                    .doOnSuccess(identifierProto -> {
                        log.info("Identifier updated: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error updating identifier: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try{
            identifierService.delete(IdentifierMapper.deleteProtoToUUID(deleteProto))
                    .doOnSuccess(identifierProto -> {
                        log.info("Identifier deleted: {}", identifierProto);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error deleting identifier: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
    public void findById(FindByIdProto findByIdProto, StreamObserver<IdentifierProto> streamObserver){
        try{
            identifierService.findById(IdentifierMapper.findByIdProtoToUUID(findByIdProto))
                    .map(IdentifierMapper::entityToProto)
                    .doOnSuccess(identifierProto -> {
                        log.info("Identifier found: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding identifier: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
                    .doOnError(error -> {
                        log.error("Error finding identifiers: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try{
            identifierService.findByName(IdentifierMapper.findByNameProtoToString(findByNameProto))
                    .map(IdentifierMapper::entityToProto)
                    .doOnNext(identifierProto -> {
                        log.info("Identifier found: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All identifiers found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding identifiers: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
        try{
            identifierService.findByValue(IdentifierMapper.findIdsByValueProtoToString(findByValueProto))
                    .map(IdentifierMapper::entityToProto)
                    .doOnNext(identifierProto -> {
                        log.info("Identifier found: {}", identifierProto);
                        streamObserver.onNext(identifierProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All identifiers found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding identifiers: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
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
