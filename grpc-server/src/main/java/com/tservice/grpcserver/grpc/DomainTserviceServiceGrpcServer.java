package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.DomainTserviceEntity;
import com.tservice.grpcserver.mappers.DomainTserviceMapper;
import com.tservice.grpcserver.services.DomainTserviceServiceImpl;
import com.tservice.proto.domaintservice.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@GrpcService
public class DomainTserviceServiceGrpcServer extends DomainTserviceServiceGrpc.DomainTserviceServiceImplBase {

    private final DomainTserviceServiceImpl domainTserviceService;

    @Override
    public void create(CreateProto createProto, StreamObserver<DomainTserviceProto> streamObserver){
        log.info("Creating DomainTservice: {}", createProto);
        DomainTserviceEntity domainTserviceEntity = DomainTserviceMapper.createProtoToEntity(createProto);
        try{
            domainTserviceService.save(domainTserviceEntity)
                    .map(DomainTserviceMapper::entityToProto)
                    .doOnSuccess(domainTserviceProto -> {
                        log.info("DomainTservice created: {}", domainTserviceProto);
                        streamObserver.onNext(domainTserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating domainTservice: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error creating domainTservice: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<DomainTserviceProto> streamObserver){
        log.info("Updating DomainTservice: {}", updateProto);
        DomainTserviceEntity domainTserviceEntity = DomainTserviceMapper.updateProtoToEntity(updateProto);
        try{
            domainTserviceService.update(domainTserviceEntity)
                    .map(DomainTserviceMapper::entityToProto)
                    .doOnSuccess(domainTserviceProto -> {
                        log.info("DomainTservice updated: {}", domainTserviceProto);
                        streamObserver.onNext(domainTserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating domainTservice: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error updating domainTservice: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver){
        log.info("Deleting DomainTservice: {}", deleteProto);
        UUID uuid = DomainTserviceMapper.deleteProtoToUUID(deleteProto);
        try{
            domainTserviceService.delete(uuid)
                    .doOnSuccess(aVoid -> {
                        log.info("DomainTservice deleted: {}", deleteProto);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting domainTservice: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error deleting domainTservice: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findById(FindByIdProto findByIdProto, StreamObserver<DomainTserviceProto> streamObserver){
        log.info("Finding DomainTservice: {}", findByIdProto);
        UUID uuid = DomainTserviceMapper.findByIdProtoToUUID(findByIdProto);
        try{
            domainTserviceService.findById(uuid)
                    .map(DomainTserviceMapper::entityToProto)
                    .doOnSuccess(domainTserviceProto -> {
                        log.info("DomainTservice found: {}", domainTserviceProto);
                        streamObserver.onNext(domainTserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding domainTservice: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding domainTservice: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<DomainTserviceProto> streamObserver){
        log.info("Finding DomainTservice: {}", findAllProto);
        try{
            domainTserviceService.findAll()
                    .map(DomainTserviceMapper::entityToProto)
                    .doOnNext(domainTserviceProto -> {
                        log.info("DomainTservice found: {}", domainTserviceProto);
                        streamObserver.onNext(domainTserviceProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All DomainTservices found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding domainTservice: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding domainTservice: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findByDomainId(FindByDomainIdProto findByDomainIdProto, StreamObserver<DomainTserviceProto> streamObserver){
        log.info("Finding DomainTservice by domain id: {}", findByDomainIdProto);
        UUID uuid = DomainTserviceMapper.findByDomainIdProtoToUUID(findByDomainIdProto);
        try{
            domainTserviceService.findByDomainId(uuid)
                    .map(DomainTserviceMapper::entityToProto)
                    .doOnNext(domainTserviceProto -> {
                        log.info("DomainTservice found: {}", domainTserviceProto);
                        streamObserver.onNext(domainTserviceProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All DomainTservices found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding domainTservice: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding domainTservice: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

    @Override
    public void findByTserviceId(FindByTserviceIdProto findByTserviceIdProto, StreamObserver<DomainTserviceProto> streamObserver){
        log.info("Finding DomainTservice by tservice id: {}", findByTserviceIdProto);
        UUID uuid = DomainTserviceMapper.findByTserviceIdProtoToUUID(findByTserviceIdProto);
        try{
            domainTserviceService.findByTserviceId(uuid)
                    .map(DomainTserviceMapper::entityToProto)
                    .doOnNext(domainTserviceProto -> {
                        log.info("DomainTservice found: {}", domainTserviceProto);
                        streamObserver.onNext(domainTserviceProto);
                    })
                    .doOnComplete(() -> {
                        log.info("All DomainTservices found");
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding domainTservice: {}", throwable.getMessage());
                        streamObserver.onError(Status.INTERNAL
                                .withDescription("Internal Server Error")
                                .augmentDescription(throwable.getMessage())
                                    .asRuntimeException());
                    })
                    .subscribe();
        } catch (Exception e){
            log.error("Error finding domainTservice: {}", e.getMessage());
            streamObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .augmentDescription(e.getMessage())
                        .asRuntimeException());
        }
    }

}
