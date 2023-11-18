package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.DomainTserviceEntity;
import com.tservice.proto.domaintservice.DomainTserviceProto;
import com.tservice.proto.domaintservice.CreateProto;
import com.tservice.proto.domaintservice.DeleteProto;
import com.tservice.proto.domaintservice.UpdateProto;
import com.tservice.proto.domaintservice.FindByIdProto;
import com.tservice.proto.domaintservice.FindByDomainIdProto;
import com.tservice.proto.domaintservice.FindByTserviceIdProto;

import java.util.UUID;

public class DomainTserviceMapper {

    public static DomainTserviceEntity protoToEntity(DomainTserviceProto domainTserviceProto){
        return DomainTserviceEntity.builder()
                .uuid(UUID.fromString(domainTserviceProto.getId()))
                .domainId(UUID.fromString(domainTserviceProto.getDomainId()))
                .tserviceId(UUID.fromString(domainTserviceProto.getTserviceId()))
                .build();
    }

    public static DomainTserviceProto entityToProto(DomainTserviceEntity domainTserviceEntity){
        return DomainTserviceProto.newBuilder()
                .setId(domainTserviceEntity.getUuid().toString())
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
                .uuid(UUID.fromString(updateProto.getId()))
                .domainId(UUID.fromString(updateProto.getDomainId()))
                .tserviceId(UUID.fromString(updateProto.getTserviceId()))
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID findByIdProtoToUUID(FindByIdProto findByIdProto){
        return UUID.fromString(findByIdProto.getId());
    }

    public static UUID findByDomainIdProtoToUUID(FindByDomainIdProto findByDomainIdProto){
        return UUID.fromString(findByDomainIdProto.getDomainId());
    }

    public static UUID findByTserviceIdProtoToUUID(FindByTserviceIdProto findByTserviceIdProto){
        return UUID.fromString(findByTserviceIdProto.getTserviceId());
    }
}
