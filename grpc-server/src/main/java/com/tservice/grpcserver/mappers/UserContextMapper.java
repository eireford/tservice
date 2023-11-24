package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.UserContextEntity;
import com.tservice.proto.usercontext.*;

import java.util.UUID;

public class UserContextMapper {

    public static UserContextEntity protoToEntity(UserContextProto userContextProto){
        return UserContextEntity.builder()
                .id(UUID.fromString(userContextProto.getId()))
                .userId(UUID.fromString(userContextProto.getUserId()))
                .contextId(UUID.fromString(userContextProto.getContextId()))
                .build();
    }

    public static UserContextProto entityToProto(UserContextEntity userContextEntity) {
        return UserContextProto.newBuilder()
                .setId(userContextEntity.getId().toString())
                .setUserId(userContextEntity.getUserId().toString())
                .setContextId(userContextEntity.getContextId().toString())
                .build();
    }

    public static UserContextEntity createProtoToEntity(CreateProto createProto){
        return UserContextEntity.builder()
                .userId(UUID.fromString(createProto.getUserId()))
                .contextId(UUID.fromString(createProto.getContextId()))
                .build();
    }

    public static UserContextEntity updateProtoToEntity(UpdateProto updateProto){
        return UserContextEntity.builder()
                .id(UUID.fromString(updateProto.getId()))
                .userId(UUID.fromString(updateProto.getUserId()))
                .contextId(UUID.fromString(updateProto.getContextId()))
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID getByIdProtoToUUID(GetByIdProto getByIdProto){
        return UUID.fromString(getByIdProto.getId());
    }

    public static UUID findByUserIdProtoToUUID(FindByUserIdProto findByUserIdProto){
        return UUID.fromString(findByUserIdProto.getUserId());
    }

    public static UUID findByContextIdProtoToUUID(FindByContextIdProto userContextProto){
        return UUID.fromString(userContextProto.getContextId());
    }
}
