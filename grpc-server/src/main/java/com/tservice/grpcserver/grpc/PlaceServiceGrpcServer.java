package com.tservice.grpcserver.grpc;

import com.tservice.grpcserver.entities.PlaceEntity;
import com.tservice.grpcserver.mappers.PlaceMapper;
import com.tservice.grpcserver.services.PlaceService;
import com.tservice.grpcserver.services.PlaceServiceImpl;
import com.tservice.proto.place.CreateProto;
import com.tservice.proto.place.PlaceServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class PlaceServiceGrpcServer extends PlaceServiceGrpc.PlaceServiceImplBase {

    private final PlaceServiceImpl placeService;

}