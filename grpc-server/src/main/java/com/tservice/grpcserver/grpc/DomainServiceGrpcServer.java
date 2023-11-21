package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.DomainEntity;
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
        log.info("Creating domain: {}", createProto);
        DomainEntity domainEntity = DomainMapper.createProtoToEntity(createProto);
        try{
            domainService.save(domainEntity)
                    .map(DomainMapper::entityToProto)
                    .doOnSuccess(domainProto -> {
                        log.info("Domain created: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating domain: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
        log.info("Updating domain: {}", updateProto);
        DomainEntity domainEntity = DomainMapper.updateProtoToEntity(updateProto);
        try{
            domainService.update(domainEntity)
                    .map(DomainMapper::entityToProto)
                    .doOnSuccess(domainProto -> {
                        log.info("Domain updated: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating domain: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
        log.info("Deleting domain: {}", deleteProto);
        UUID uuid = DomainMapper.deleteProtoToUUID(deleteProto);
        try{
            domainService.delete(uuid)
                    .doOnSuccess(domainProto -> {
                        log.info("Domain deleted: {}", domainProto);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting domain: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
        log.info("Finding domain: {}", findByIdProto);
        UUID uuid = DomainMapper.findByIdProtoToUUID(findByIdProto);
        try{
            domainService.findById(uuid)
                    .map(DomainMapper::entityToProto)
                    .doOnSuccess(domainProto -> {
                        log.info("Domain found: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding domain: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
        log.info("Finding domains");
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
                    .doOnError(throwable -> {
                        log.error("Error finding domains: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
        log.info("Finding domain: {}", findByNameProto);
        String name = DomainMapper.findByNameProtoToString(findByNameProto);
        try{
            domainService.findByName(name)
                    .map(DomainMapper::entityToProto)
                    .doOnNext(domainProto -> {
                        log.info("Domain found: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding domain: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
        log.info("Finding domain: {}", findByValueProto);
        String value = DomainMapper.findByValueProtoToString(findByValueProto);
        try{
            domainService.findByValue(value)
                    .map(DomainMapper::entityToProto)
                    .doOnNext(domainProto -> {
                        log.info("Domain found: {}", domainProto);
                        streamObserver.onNext(domainProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding domain: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
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
