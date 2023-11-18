package com.tservice.grpcserver.mappers;
import com.tservice.grpcserver.entities.TsetPlaceEntity;
import com.tservice.proto.tsetplace.*;

import java.util.UUID;

public class TsetPlaceMapper {

    public static TsetPlaceEntity protoToEntity(TsetPlaceProto tsetPlaceProto){
        return TsetPlaceEntity.builder()
                .uuid(tsetPlaceProto.getId())
                .tsetId(UUID.fromString(tsetPlaceProto.getTsetID()))
                .placeId(UUID.fromString(tsetPlaceProto.getPlaceID()))
                .build();
    }

    public static TsetPlaceProto entityToProto(TsetPlaceEntity tsetPlaceEntity){
        return TsetPlaceProto.newBuilder()
                .setId(tsetPlaceEntity.getUuid())
                .setTsetID(tsetPlaceEntity.getTsetId().toString())
                .setPlaceID(tsetPlaceEntity.getPlaceId().toString())
                .build();
    }

    public static TsetPlaceEntity createProtoToEntity(CreateProto createProto){
        return TsetPlaceEntity.builder()
                .tsetId(UUID.fromString(createProto.getTsetID()))
                .placeId(UUID.fromString(createProto.getPlaceID()))
                .build();
    }

    public static TsetPlaceEntity updateProtoToEntity(UpdateProto updateProto){
        return TsetPlaceEntity.builder()
                .uuid(updateProto.getId())
                .tsetId(UUID.fromString(updateProto.getTsetID()))
                .placeId(UUID.fromString(updateProto.getPlaceID()))
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID findByIdProtoToUUID(FindByIdProto findByIdProto){
        return UUID.fromString(findByIdProto.getId());
    }

    public static UUID findByTsetIdProtoToUUID(FindByTsetIdProto findByTsetIdProto){
        return UUID.fromString(findByTsetIdProto.getTsetId());
    }

    public static UUID findByPlaceIdProtoToUUID(FindByPlaceIdProto findByPlaceIdProto){
        return UUID.fromString(findByPlaceIdProto.getPlaceId());
    }

}
