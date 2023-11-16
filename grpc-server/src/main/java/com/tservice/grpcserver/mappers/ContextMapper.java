package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.Context;
import com.tservice.proto.ContextProto;

import java.util.UUID;

public class ContextMapper {

    public static Context contextProtoToContextEntity(ContextProto contextProto){
        return Context.builder()
                .contextId(UUID.fromString(contextProto.getContextId()))
                .name(contextProto.getName())
                .value(contextProto.getValue())
                .build();
    }

    public static ContextProto contextEntityToContextProto(Context contextEntity){
        return ContextProto.newBuilder()
                .setContextId(contextEntity.getContextId().toString())
                .setName(contextEntity.getName())
                .setValue(contextEntity.getValue())
                .build();
    }

    public static Context createContextProtoToContextEntity(ContextProto contextProto){
        return Context.builder()
                .name(contextProto.getName())
                .value(contextProto.getValue())
                .build();
    }

    public static Context updateContextProtoToContextEntity(ContextProto contextProto){
        return Context.builder()
                .contextId(UUID.fromString(contextProto.getContextId()))
                .name(contextProto.getName())
                .value(contextProto.getValue())
                .build();
    }

    public static UUID deleteContextProtoToUUID(ContextProto contextProto){
        return UUID.fromString(contextProto.getContextId());
    }

    public static UUID getContextByContextIdProtoToUUID(ContextProto contextProto){
        return UUID.fromString(contextProto.getContextId());
    }

    public static String findContextsByNameProtoToString(ContextProto contextProto){
        return contextProto.getName();
    }

    public static String findContextsByValueProtoToString(ContextProto contextProto){
        return contextProto.getValue();
    }
}
