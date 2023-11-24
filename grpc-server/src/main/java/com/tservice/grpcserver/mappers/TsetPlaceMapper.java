package com.tservice.grpcserver.mappers;
import com.tservice.grpcserver.entities.TsetPlaceEntity;
import com.tservice.proto.tsetplace.*;

import java.util.UUID;

public class TsetPlaceMapper {

    public static TsetPlaceEntity protoToEntity(TsetPlaceProto tsetPlaceProto){
        return TsetPlaceEntity.builder()
                .id(UUID.fromString(tsetPlaceProto.getId()))
                .tsetId(UUID.fromString(tsetPlaceProto.getTsetId()))
                .placeId(UUID.fromString(tsetPlaceProto.getPlaceId()))
                .build();
    }

    public static TsetPlaceProto entityToProto(TsetPlaceEntity tsetPlaceEntity){
        return TsetPlaceProto.newBuilder()
                .setId(tsetPlaceEntity.getId().toString())
                .setTsetId(tsetPlaceEntity.getTsetId().toString())
                .setPlaceId(tsetPlaceEntity.getPlaceId().toString())
                .build();
    }

    public static TsetPlaceEntity createProtoToEntity(CreateProto createProto){
        return TsetPlaceEntity.builder()
                .tsetId(UUID.fromString(createProto.getTsetId()))
                .placeId(UUID.fromString(createProto.getPlaceId()))
                .build();
    }

    public static TsetPlaceEntity updateProtoToEntity(UpdateProto updateProto){
        return TsetPlaceEntity.builder()
                .id(UUID.fromString(updateProto.getId()))
                .tsetId(UUID.fromString(updateProto.getTsetId()))
                .placeId(UUID.fromString(updateProto.getPlaceId()))
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID getByIdProtoToUUID(GetByIdProto getByIdProto){
        return UUID.fromString(getByIdProto.getId());
    }

    public static UUID findByTsetIdProtoToUUID(FindByTsetIdProto findByTsetIdProto){
        return UUID.fromString(findByTsetIdProto.getTsetId());
    }

    public static UUID findByPlaceIdProtoToUUID(FindByPlaceIdProto findByPlaceIdProto){
        return UUID.fromString(findByPlaceIdProto.getPlaceId());
    }

}
