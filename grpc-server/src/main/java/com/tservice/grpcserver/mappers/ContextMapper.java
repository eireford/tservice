package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.ContextEntity;
import com.tservice.proto.context.*;

import java.util.UUID;

public class ContextMapper {

    public static ContextEntity protoToEntity(ContextProto contextProto){
        return ContextEntity.builder()
                .id(UUID.fromString(contextProto.getId()))
                .name(contextProto.getName())
                .value(contextProto.getValue())
                .build();
    }

    public static ContextProto entityToProto(ContextEntity contextEntity){
        return ContextProto.newBuilder()
                .setId(contextEntity.getId().toString())
                .setName(contextEntity.getName())
                .setValue(contextEntity.getValue())
                .build();
    }

    public static ContextEntity createProtoToEntity(CreateProto createProto){
        return ContextEntity.builder()
                .name(createProto.getName())
                .value(createProto.getValue())
                .build();
    }

    public static ContextEntity updateProtoToEntity(UpdateProto updateProto){
        return ContextEntity.builder()
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
