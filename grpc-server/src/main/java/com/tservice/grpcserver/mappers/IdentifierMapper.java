package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.Identifier;
import com.tservice.proto.IdentifierProto;

import java.util.UUID;

public class IdentifierMapper {

    public static Identifier identifierProtoToIdentifierEntity(IdentifierProto identifierProto){
        return Identifier.builder()
                .identifierId(UUID.fromString(identifierProto.getIdentifierId()))
                .contextId(UUID.fromString(identifierProto.getContextId()))
                .name(identifierProto.getName())
                .value(identifierProto.getValue())
                .build();
    }

    public static IdentifierProto identifierEntityToIdentifierProto(Identifier identifierEntity){
        return IdentifierProto.newBuilder()
                .setIdentifierId(identifierEntity.getIdentifierId().toString())
                .setContextId(identifierEntity.getContextId().toString())
                .setName(identifierEntity.getName())
                .setValue(identifierEntity.getValue())
                .build();
    }

    public static Identifier createIdentifierProtoToIdentifierEntity(IdentifierProto identifierProto){
        return Identifier.builder()
                .contextId(UUID.fromString(identifierProto.getContextId()))
                .name(identifierProto.getName())
                .value(identifierProto.getValue())
                .build();
    }

    public static Identifier updateIdentifierProtoToIdentifierEntity(IdentifierProto identifierProto){
        return Identifier.builder()
                .identifierId(UUID.fromString(identifierProto.getIdentifierId()))
                .contextId(UUID.fromString(identifierProto.getContextId()))
                .name(identifierProto.getName())
                .value(identifierProto.getValue())
                .build();
    }

    public static UUID deleteIdentifierProtoToUUID(IdentifierProto identifierProto){
        return UUID.fromString(identifierProto.getIdentifierId());
    }

    public static UUID getIdentifierByIdentifierIdProtoToUUID(IdentifierProto identifierProto){
        return UUID.fromString(identifierProto.getIdentifierId());
    }

    public static UUID findIdentifiersByContextIdProtoToUUID(IdentifierProto identifierProto){
        return UUID.fromString(identifierProto.getContextId());
    }

    public static UUID findIdentifiersByUserIdProtoToUUID(IdentifierProto identifierProto){
        return UUID.fromString(identifierProto.getUserId());
    }

    public static String findIdentifiersByNamesProtoToString(IdentifierProto identifierProto){
        return identifierProto.getName();
    }

    public static String findIdentifiersByValueProtoToString(IdentifierProto identifierProto){
        return identifierProto.getValue();
    }
}
