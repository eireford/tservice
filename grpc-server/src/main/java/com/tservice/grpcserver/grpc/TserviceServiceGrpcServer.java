package com.tservice.grpcserver.grpc;

import com.google.protobuf.Empty;
import com.tservice.grpcserver.entities.TserviceEntity;
import com.tservice.grpcserver.mappers.TserviceMapper;
import com.tservice.grpcserver.services.TserviceServiceImpl;
import com.tservice.proto.tservice.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class TserviceServiceGrpcServer extends TserviceServiceGrpc.TserviceServiceImplBase{

    private final TserviceServiceImpl tserviceService;

    @Override
    public void create(CreateProto createProto, StreamObserver<TserviceProto> streamObserver) {
        log.info("Creating Tservice: {}", createProto);
        TserviceEntity tserviceEntity = TserviceMapper.createProtoToEntity(createProto);
        try {
            tserviceService.save(tserviceEntity)
                    .map(TserviceMapper::entityToProto)
                    .doOnSuccess(tserviceProto -> {
                        log.info("Tservice created: {}", tserviceProto);
                        streamObserver.onNext(tserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error creating Tservice: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error creating Tservice: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void update(UpdateProto updateProto, StreamObserver<TserviceProto> streamObserver) {
        log.info("Updating Tservice: {}", updateProto);
        TserviceEntity tserviceEntity = TserviceMapper.updateProtoToEntity(updateProto);
        try {
            tserviceService.update(tserviceEntity)
                    .map(TserviceMapper::entityToProto)
                    .doOnSuccess(tserviceProto -> {
                        log.info("Tservice updated: {}", tserviceProto);
                        streamObserver.onNext(tserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error updating Tservice: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error updating Tservice: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void delete(DeleteProto deleteProto, StreamObserver<Empty> streamObserver) {
        log.info("Deleting Tservice: {}", deleteProto);
        UUID uuid = UUID.fromString(deleteProto.getId());
        try {
            tserviceService.delete(uuid)
                    .doOnSuccess(tserviceProto -> {
                        log.info("Tservice deleted: {}", tserviceProto);
                        streamObserver.onNext(Empty.newBuilder().build());
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error deleting Tservice: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error deleting Tservice: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findById(FindByIdProto findByIdProto, StreamObserver<TserviceProto> streamObserver) {
        log.info("Finding Tservice by id: {}", findByIdProto);
        UUID uuid = UUID.fromString(findByIdProto.getId());
        try {
            tserviceService.findById(uuid)
                    .map(TserviceMapper::entityToProto)
                    .doOnSuccess(tserviceProto -> {
                        log.info("Tservice found: {}", tserviceProto);
                        streamObserver.onNext(tserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding Tservice: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding Tservice: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findAll(FindAllProto findAllProto, StreamObserver<TserviceProto> streamObserver) {
        log.info("Finding all Tservices");
        try {
            tserviceService.findAll()
                    .map(TserviceMapper::entityToProto)
                    .doOnNext(tserviceProto -> {
                        log.info("Tservices found: {}", tserviceProto);
                        streamObserver.onNext(tserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding Tservices: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding Tservices: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findByName(FindByNameProto findByNameProto, StreamObserver<TserviceProto> streamObserver) {
        log.info("Finding Tservice by name: {}", findByNameProto);
        String name = findByNameProto.getName();
        try {
            tserviceService.findByName(name)
                    .map(TserviceMapper::entityToProto)
                    .doOnNext(tserviceProto -> {
                        log.info("Tservice found: {}", tserviceProto);
                        streamObserver.onNext(tserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding Tservice: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding Tservice: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }

    @Override
    public void findByValue(FindByValueProto findByValueProto, StreamObserver<TserviceProto> streamObserver) {
        log.info("Finding Tservice by value: {}", findByValueProto);
        String value = findByValueProto.getValue();
        try {
            tserviceService.findByValue(value)
                    .map(TserviceMapper::entityToProto)
                    .doOnNext(tserviceProto -> {
                        log.info("Tservice found: {}", tserviceProto);
                        streamObserver.onNext(tserviceProto);
                        streamObserver.onCompleted();
                    })
                    .doOnError(throwable -> {
                        log.error("Error finding Tservice: {}", throwable.getMessage());
                        streamObserver.onError(throwable);
                    })
                    .subscribe();
        } catch (Exception e) {
            log.error("Error finding Tservice: {}", e.getMessage());
            streamObserver.onError(e);
        }
    }
}
