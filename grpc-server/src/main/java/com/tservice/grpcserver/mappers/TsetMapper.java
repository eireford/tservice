package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.TsetEntity;
import com.tservice.proto.tset.*;

import java.util.UUID;

public class TsetMapper {

    public static TsetEntity protoToEntity(TsetProto tsetProto){
        return TsetEntity.builder()
                .id(UUID.fromString(tsetProto.getId()))
                .name(tsetProto.getName())
                .value(tsetProto.getValue())
                .build();
    }

    public static TsetProto entityToProto(TsetEntity tsetEntity){
        return TsetProto.newBuilder()
                .setId(tsetEntity.getId().toString())
                .setName(tsetEntity.getName())
                .setValue(tsetEntity.getValue())
                .build();
    }

    public static TsetEntity createProtoToEntity(CreateProto createProto){
        return TsetEntity.builder()
                .name(createProto.getName())
                .value(createProto.getValue())
                .build();
    }

    public static TsetEntity updateProtoToEntity(UpdateProto updateProto){
        return TsetEntity.builder()
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
