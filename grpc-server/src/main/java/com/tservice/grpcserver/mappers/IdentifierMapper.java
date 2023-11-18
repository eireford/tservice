package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.IdentifierEntity;
import com.tservice.proto.identifier.*;

import java.util.UUID;

public class IdentifierMapper {

    public static IdentifierEntity protoToEntity(IdentifierProto identifierProto){
        return IdentifierEntity.builder()
                .uuid(UUID.fromString(identifierProto.getId()))
                .name(identifierProto.getName())
                .value(identifierProto.getValue())
                .build();
    }

    public static IdentifierProto entityToProto(IdentifierEntity identifierEntity){
        return IdentifierProto.newBuilder()
                .setId(identifierEntity.getUuid().toString())
                .setName(identifierEntity.getName())
                .setValue(identifierEntity.getValue())
                .build();
    }

    public static IdentifierEntity createProtoToEntity(CreateProto createProto){
        return IdentifierEntity.builder()
                .name(createProto.getName())
                .value(createProto.getValue())
                .build();
    }

    public static IdentifierEntity updateProtoToEntity(UpdateProto updateProto){
        return IdentifierEntity.builder()
                .uuid(UUID.fromString(updateProto.getId()))
                .name(updateProto.getName())
                .value(updateProto.getValue())
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID findByIdProtoToUUID(FindByIdProto findByIdProto){
        return UUID.fromString(findByIdProto.getId());
    }

    public static String findByNameProtoToString(FindByNameProto findByNameProto){
        return findByNameProto.getName();
    }

    public static String findIdsByValueProtoToString(FindByValueProto findByValueProto){
        return findByValueProto.getValue();
    }
}
