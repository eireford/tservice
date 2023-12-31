package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.TserviceEntity;
import com.tservice.proto.tservice.*;

import java.util.UUID;

public class TserviceMapper {

    public static TserviceEntity protoToEntity(TserviceProto tserviceProto){
        return TserviceEntity.builder()
                .id(UUID.fromString(tserviceProto.getId()))
                .name(tserviceProto.getName())
                .value(tserviceProto.getValue())
                .build();
    }

    public static TserviceProto entityToProto(TserviceEntity tserviceEntity){
        return TserviceProto.newBuilder()
                .setId(tserviceEntity.getId().toString())
                .setName(tserviceEntity.getName())
                .setValue(tserviceEntity.getValue())
                .build();
    }

    public static TserviceEntity createProtoToEntity(CreateProto createProto){
        return TserviceEntity.builder()
                .name(createProto.getName())
                .value(createProto.getValue())
                .build();
    }

    public static TserviceEntity updateProtoToEntity(UpdateProto updateProto){
        return TserviceEntity.builder()
                .id(UUID.fromString(updateProto.getId()))
                .name(updateProto.getName())
                .value(updateProto.getValue())
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID getByIdProtoToUUID(GetByIdProto getByIdProto){
        return UUID.fromString(getByIdProto.getId());
    }

    public static String findByNameProtoToString(FindByNameProto findByNameProto){
        return findByNameProto.getName();
    }

    public static String findByValueProtoToString(FindByValueProto findByValueProto){
        return findByValueProto.getValue();
    }
}
