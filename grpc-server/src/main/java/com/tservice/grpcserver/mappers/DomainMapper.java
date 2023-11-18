package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.DomainEntity;
import com.tservice.proto.domain.*;


import java.util.UUID;

public class DomainMapper {

    public static DomainEntity protoToEntity(DomainProto domainProto){
        return DomainEntity.builder()
                .uuid(UUID.fromString(domainProto.getId()))
                .name(domainProto.getName())
                .value(domainProto.getValue())
                .build();
    }

    public static DomainProto entityToProto(DomainEntity domainEntity){
        return DomainProto.newBuilder()
                .setId(domainEntity.getUuid().toString())
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

    public static String findByValueProtoToString(FindByValueProto findByValueProto){
        return findByValueProto.getValue();
    }
}
