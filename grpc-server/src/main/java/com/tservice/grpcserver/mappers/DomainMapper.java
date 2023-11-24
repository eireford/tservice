package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.DomainEntity;
import com.tservice.proto.domain.*;


import java.util.UUID;

public class DomainMapper {

    public static DomainEntity protoToEntity(DomainProto domainProto){
        return DomainEntity.builder()
                .id(UUID.fromString(domainProto.getId()))
                .name(domainProto.getName())
                .value(domainProto.getValue())
                .build();
    }

    public static DomainProto entityToProto(DomainEntity domainEntity){
        return DomainProto.newBuilder()
                .setId(domainEntity.getId().toString())
                .setName(domainEntity.getName())
                .setValue(domainEntity.getValue())
                .build();
    }

    public static DomainEntity createProtoToEntity(CreateProto createProto){
        return DomainEntity.builder()
                .name(createProto.getName())
                .value(createProto.getValue())
                .build();
    }

    public static DomainEntity updateProtoToEntity(UpdateProto updateProto){
        return DomainEntity.builder()
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
