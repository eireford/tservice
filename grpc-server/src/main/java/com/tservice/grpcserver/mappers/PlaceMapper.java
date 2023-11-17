package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.PlaceEntity;
import com.tservice.proto.place.*;

import java.util.UUID;

public class PlaceMapper {

    public static PlaceEntity protoToEntity(PlaceProto placeProto){
        return PlaceEntity.builder()
                .uuid(UUID.fromString(placeProto.getId()))
                .name(placeProto.getName())
                .value(placeProto.getValue())
                .build();
    }

    public static PlaceProto entityToProto(PlaceEntity placeEntity){
        return PlaceProto.newBuilder()
                .setId(placeEntity.getUuid().toString())
                .setName(placeEntity.getName())
                .setValue(placeEntity.getValue())
                .build();
    }

    public static PlaceEntity createPlaceProtoToEntity(CreateProto createProto){
        return PlaceEntity.builder()
                .name(createProto.getName())
                .value(createProto.getValue())
                .build();
    }

    public static PlaceEntity updatePlaceProtoToEntity(UpdateProto updateProto){
        return PlaceEntity.builder()
                .uuid(UUID.fromString(updateProto.getId()))
                .name(updateProto.getName())
                .value(updateProto.getValue())
                .build();
    }

    public static UUID deletePlaceProtoToUUID(DeleteProto deleteProto){
        return UUID.fromString(deleteProto.getId());
    }

    public static UUID findByIdProtoToUUID(FindByIdProto findByIdProto){
        return UUID.fromString(findByIdProto.getId());
    }

    public static String findByNameProtoToString(FindByNameProto findByNameProto){
        return findByNameProto.getName();
    }

    public static String findByValueProtoToString(FindByValueProto findByValueProto){
        return findByValueProto.getValue();
    }
}
