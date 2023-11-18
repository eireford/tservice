package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.UserContextEntity;
import com.tservice.proto.usercontext.UserContextProto;

import java.util.UUID;

public class UserContextMapper {

    public static UserContextEntity protoToEntity(UserContextProto userContextProto){
        return UserContextEntity.builder()
                .uuid(UUID.fromString(userContextProto.getId()))
                .userId(UUID.fromString(userContextProto.getUserId()))
                .contextId(UUID.fromString(userContextProto.getContextId()))
                .build();
    }

    public static UserContextProto entityToProto(UserContextEntity userContextEntity) {
        return UserContextProto.newBuilder()
                .setId(userContextEntity.getUuid().toString())
                .setUserId(userContextEntity.getUserId().toString())
                .setContextId(userContextEntity.getContextId().toString())
                .build();
    }

    public static UserContextEntity createProtoToEntity(UserContextProto userContextProto){
        return UserContextEntity.builder()
                .userId(UUID.fromString(userContextProto.getUserId()))
                .contextId(UUID.fromString(userContextProto.getContextId()))
                .build();
    }

    public static UserContextEntity updateProtoToEntity(UserContextProto userContextProto){
        return UserContextEntity.builder()
                .uuid(UUID.fromString(userContextProto.getId()))
                .userId(UUID.fromString(userContextProto.getUserId()))
                .contextId(UUID.fromString(userContextProto.getContextId()))
                .build();
    }

    public static UUID deleteProtoToUUID(UserContextProto userContextProto){
        return UUID.fromString(userContextProto.getId());
    }

    public static UUID findByIdProtoToUUID(UserContextProto userContextProto){
        return UUID.fromString(userContextProto.getId());
    }

    public static UUID findByUserIdProtoToUUID(UserContextProto userContextProto){
        return UUID.fromString(userContextProto.getUserId());
    }

    public static UUID findByContextIdProtoToUUID(UserContextProto userContextProto){
        return UUID.fromString(userContextProto.getContextId());
    }
}
