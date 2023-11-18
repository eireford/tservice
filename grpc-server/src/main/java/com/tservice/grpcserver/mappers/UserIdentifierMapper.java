package com.tservice.grpcserver.mappers;
import com.tservice.grpcserver.entities.UserIdentifierEntity;
import com.tservice.proto.useridentifier.*;

import java.util.UUID;

public class UserIdentifierMapper {

    public static UserIdentifierEntity protoToEntity(UserIdentifierProto userIdentifierProto){
        return UserIdentifierEntity.builder()
                .uuid(UUID.fromString(userIdentifierProto.getId()))
                .userId(UUID.fromString(userIdentifierProto.getUserId()))
                .identifierId(UUID.fromString(userIdentifierProto.getIdentifierId()))
                .build();
    }

    public static UserIdentifierProto entityToProto(UserIdentifierEntity userIdentifierEntity){
        return UserIdentifierProto.newBuilder()
                .setId(userIdentifierEntity.getUuid().toString())
                .setUserId(userIdentifierEntity.getUserId().toString())
                .setIdentifierId(userIdentifierEntity.getIdentifierId().toString())
                .build();
    }

    public static UserIdentifierEntity createProtoToEntity(CreateProto createProto){
        return UserIdentifierEntity.builder()
                .userId(UUID.fromString(createProto.getUserId()))
                .identifierId(UUID.fromString(createProto.getIdentifierId()))
                .build();
    }

    public static UserIdentifierEntity updateProtoToEntity(UpdateProto updateProto){
        return UserIdentifierEntity.builder()
                .uuid(UUID.fromString(updateProto.getId()))
                .userId(UUID.fromString(updateProto.getUserId()))
                .identifierId(UUID.fromString(updateProto.getIdentifierId()))
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID findByIdProtoToUUID(FindByIdProto findByIdProto){
        return UUID.fromString(findByIdProto.getId());
    }

    public static UUID findByUserIdProtoToUUID(FindByUserIdProto findByUserIdProto){
        return UUID.fromString(findByUserIdProto.getUserId());
    }

    public static UUID findByIdentifierIdProtoToUUID(FindByIdentifierIdProto findByIdentifierIdProto){
        return UUID.fromString(findByIdentifierIdProto.getIdentifierId());
    }
}
