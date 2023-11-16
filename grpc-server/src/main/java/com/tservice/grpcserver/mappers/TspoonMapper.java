package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.Tspoon;
import com.tservice.proto.TspoonProto;

import java.util.UUID;

public class TspoonMapper {
    
    public static Tspoon tspoonProtoToTspoonEntity(TspoonProto tspoonProto){
        return Tspoon.builder()
                .tspoonId(UUID.fromString(tspoonProto.getTspoonId()))
                .contextId(UUID.fromString(tspoonProto.getContextId()))
                .name(tspoonProto.getName())
                .value(tspoonProto.getValue())
                .build();
    }

    public static TspoonProto tspoonEntityToTspoonProto(Tspoon tspoonEntity){
        return TspoonProto.newBuilder()
                .setTspoonId(tspoonEntity.getTspoonId().toString())
                .setContextId(tspoonEntity.getContextId().toString())
                .setName(tspoonEntity.getName())
                .setValue(tspoonEntity.getValue())
                .build();
    }

    public static Tspoon createTspoonProtoToTspoonEntity(TspoonProto tspoonProto){
        return Tspoon.builder()
                .contextId(UUID.fromString(tspoonProto.getContextId()))
                .name(tspoonProto.getName())
                .value(tspoonProto.getValue())
                .build();
    }

    public static Tspoon updateTspoonProtoToTspoonEntity(TspoonProto tspoonProto){
        return Tspoon.builder()
                .tspoonId(UUID.fromString(tspoonProto.getTspoonId()))
                .contextId(UUID.fromString(tspoonProto.getContextId()))
                .name(tspoonProto.getName())
                .value(tspoonProto.getValue())
                .build();
    }

    public static UUID deleteTspoonByTspoonIdProtoToUUID(TspoonProto tspoonProto){
        return UUID.fromString(tspoonProto.getTspoonId());
    }

    public static UUID getTspoonByTspoonIdProtoToUUID(TspoonProto tspoonProto){
        return UUID.fromString(tspoonProto.getTspoonId());
    }

    public static UUID findTspoonsByContextIdProtoToUUID(TspoonProto tspoonProto){
        return UUID.fromString(tspoonProto.getContextId());
    }

    public static UUID findTspoonsByContextIdAndNameProtoToUUID(TspoonProto tspoonProto){
        return UUID.fromString(tspoonProto.getContextId());
    }

    public static String findTspoonsByNamesProtoToString(TspoonProto tspoonProto){
        return tspoonProto.getName();
    }

    public static String findTspoonsByValueProtoToString(TspoonProto tspoonProto){
        return tspoonProto.getValue();
    }
    
}
