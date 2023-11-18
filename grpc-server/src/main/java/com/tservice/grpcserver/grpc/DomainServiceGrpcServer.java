package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.mappers.DomainMapper;
import com.tservice.grpcserver.services.DomainServiceImpl;
import com.tservice.proto.domain.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@GrpcService
public class DomainServiceGrpcServer extends DomainServiceGrpc.DomainServiceImplBase{

    private final DomainServiceImpl domainService;

    @Override
    public void create(CreateProto createProto, StreamObserver<DomainProto> streamObserver){
        try{
            domainService.save(DomainMapper.createProtoToEntity(createProto))
                    .map(DomainMapper::entityToProto)
                    .doOnSuccess(domainProto -> {
                        log.info("Domain created: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error creating domain: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error creating domain: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<DomainProto> streamObserver){
        try{
            domainService.update(DomainMapper.updateProtoToEntity(updateProto))
                    .map(DomainMapper::entityToProto)
                    .doOnSuccess(domainProto -> {
                        log.info("Domain updated: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error updating domain: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error updating domain: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver){
        try{
            domainService.delete(DomainMapper.deleteProtoToUUID(deleteProto))
                    .doOnSuccess(domainProto -> {
                        log.info("Domain deleted: {}", domainProto);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error deleting domain: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error deleting domain: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findById(FindByIdProto findByIdProto, StreamObserver<DomainProto> streamObserver){
        try{
            domainService.findById(DomainMapper.findByIdProtoToUUID(findByIdProto))
                    .map(DomainMapper::entityToProto)
                    .doOnSuccess(domainProto -> {
                        log.info("Domain found: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding domain: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding domain: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<DomainProto> streamObserver){
        try{
            domainService.findAll()
                    .map(DomainMapper::entityToProto)
                    .doOnNext(domainProto -> {
                        log.info("Domain found: {}", domainProto);
                        streamObserver.onNext(domainProto);
                    })
                    .doOnComplete(() -> {
                        log.info("Domains found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding domains: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding domains: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findByName(FindByNameProto findByNameProto, StreamObserver<DomainProto> streamObserver){
        try{
            domainService.findByName(DomainMapper.findByNameProtoToString(findByNameProto))
                    .map(DomainMapper::entityToProto)
                    .doOnNext(domainProto -> {
                        log.info("Domain found: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding domain: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding domain: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findByValue(FindByValueProto findByValueProto, StreamObserver<DomainProto> streamObserver){
        try{
            domainService.findByValue(DomainMapper.findByValueProtoToString(findByValueProto))
                    .map(DomainMapper::entityToProto)
                    .doOnNext(domainProto -> {
                        log.info("Domain found: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(error -> {
                        log.error("Error finding domain: {}", error.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(error.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding domain: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

}
