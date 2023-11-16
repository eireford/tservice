package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.Tset;
import com.tservice.proto.TsetProto;

import java.util.UUID;

public class TsetMapper {

    public static Tset tsetProtoToTsetEntity(TsetProto tsetProto){
        return Tset.builder()
                .tsetId(UUID.fromString(tsetProto.getTsetId()))
                .contextId(UUID.fromString(tsetProto.getContextId()))
                .name(tsetProto.getName())
                .value(tsetProto.getValue())
                .build();
    }

    public static TsetProto tsetEntityToTsetProto(Tset tsetEntity){
        return TsetProto.newBuilder()
                .setTsetId(tsetEntity.getTsetId().toString())
                .setContextId(tsetEntity.getContextId().toString())
                .setName(tsetEntity.getName())
                .setValue(tsetEntity.getValue())
                .build();
    }

    public static Tset createTsetProtoToTsetEntity(TsetProto tsetProto){
        return Tset.builder()
                .contextId(UUID.fromString(tsetProto.getContextId()))
                .name(tsetProto.getName())
                .value(tsetProto.getValue())
                .build();
    }

    public static Tset updateTsetProtoToTsetEntity(TsetProto tsetProto){
        return Tset.builder()
                .tsetId(UUID.fromString(tsetProto.getTsetId()))
                .contextId(UUID.fromString(tsetProto.getContextId()))
                .name(tsetProto.getName())
                .value(tsetProto.getValue())
                .build();
    }

    public static UUID deleteTsetProtoToUUID(TsetProto tsetProto){
        return UUID.fromString(tsetProto.getTsetId());
    }

    public static UUID getTsetByTsetIdProtoToUUID(TsetProto tsetProto){
        return UUID.fromString(tsetProto.getTsetId());
    }

    public static UUID findTsetsByContextIdProtoToUUID(TsetProto tsetProto){
        return UUID.fromString(tsetProto.getContextId());
    }

    public static String findTsetsByNamesProtoToString(TsetProto tsetProto){
        return tsetProto.getName();
    }

    public static String findTsetsByValueProtoToString(TsetProto tsetProto){
        return tsetProto.getValue();
    }

}
