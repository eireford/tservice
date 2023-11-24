package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.DomainTserviceEntity;
import com.tservice.proto.domaintservice.*;

import java.util.UUID;

public class DomainTserviceMapper {

    public static DomainTserviceEntity protoToEntity(DomainTserviceProto domainTserviceProto){
        return DomainTserviceEntity.builder()
                .id(UUID.fromString(domainTserviceProto.getId()))
                .domainId(UUID.fromString(domainTserviceProto.getDomainId()))
                .tserviceId(UUID.fromString(domainTserviceProto.getTserviceId()))
                .build();
    }

    public static DomainTserviceProto entityToProto(DomainTserviceEntity domainTserviceEntity){
        return DomainTserviceProto.newBuilder()
                .setId(domainTserviceEntity.getId().toString())
                .setDomainId(domainTserviceEntity.getDomainId().toString())
                .setTserviceId(domainTserviceEntity.getTserviceId().toString())
                .build();
    }

    public static DomainTserviceEntity createProtoToEntity(CreateProto createProto){
        return DomainTserviceEntity.builder()
                .domainId(UUID.fromString(createProto.getDomainId()))
                .tserviceId(UUID.fromString(createProto.getTserviceId()))
                .build();
    }

    public static DomainTserviceEntity updateProtoToEntity(UpdateProto updateProto){
        return DomainTserviceEntity.builder()
                .id(UUID.fromString(updateProto.getId()))
                .domainId(UUID.fromString(updateProto.getDomainId()))
                .tserviceId(UUID.fromString(updateProto.getTserviceId()))
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID getByIdProtoToUUID(GetByIdProto getByIdProto){
        return UUID.fromString(getByIdProto.getId());
    }

    public static UUID findByDomainIdProtoToUUID(FindByDomainIdProto findByDomainIdProto){
        return UUID.fromString(findByDomainIdProto.getDomainId());
    }

    public static UUID findByTserviceIdProtoToUUID(FindByTserviceIdProto findByTserviceIdProto){
        return UUID.fromString(findByTserviceIdProto.getTserviceId());
    }
}
