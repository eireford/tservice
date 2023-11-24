package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.TspoonEntity;
import com.tservice.proto.tspoon.*;

import java.util.UUID;

public class TspoonMapper {
    
    public static TspoonEntity protoToEntity(TspoonProto tspoonProto){
        return TspoonEntity.builder()
                .id(UUID.fromString(tspoonProto.getId()))
                .name(tspoonProto.getName())
                .value(tspoonProto.getValue())
                .build();
    }

    public static TspoonProto entityToProto(TspoonEntity tspoonEntity){
        return TspoonProto.newBuilder()
                .setId(tspoonEntity.getId().toString())
                .setName(tspoonEntity.getName())
                .setValue(tspoonEntity.getValue())
                .build();
    }

    public static TspoonEntity createProtoToEntity(CreateProto createProto){
        return TspoonEntity.builder()
                .name(createProto.getName())
                .value(createProto.getValue())
                .build();
    }

    public static TspoonEntity updateProtoToEntity(UpdateProto updateProto){
        return TspoonEntity.builder()
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
