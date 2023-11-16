package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.Place;
import com.tservice.proto.PlaceProto;

import java.util.UUID;

public class PlaceMapper {

    public static Place placeProtoToPlaceEntity(PlaceProto placeProto){
        return Place.builder()
                .placeId(UUID.fromString(placeProto.getPlaceId()))
                .contextId(UUID.fromString(placeProto.getContextId()))
                .name(placeProto.getName())
                .value(placeProto.getValue())
                .build();
    }

    public static PlaceProto placeEntityToPlaceProto(Place placeEntity){
        return PlaceProto.newBuilder()
                .setPlaceId(placeEntity.getPlaceId().toString())
                .setContextId(placeEntity.getContextId().toString())
                .setName(placeEntity.getName())
                .setValue(placeEntity.getValue())
                .build();
    }

    public static Place createPlaceProtoToPlaceEntity(PlaceProto placeProto){
        return Place.builder()
                .contextId(UUID.fromString(placeProto.getContextId()))
                .name(placeProto.getName())
                .value(placeProto.getValue())
                .build();
    }

    public static Place updatePlaceProtoToPlaceEntity(PlaceProto placeProto){
        return Place.builder()
                .placeId(UUID.fromString(placeProto.getPlaceId()))
                .contextId(UUID.fromString(placeProto.getContextId()))
                .name(placeProto.getName())
                .value(placeProto.getValue())
                .build();
    }

    public static UUID deletePlaceProtoToUUID(PlaceProto placeProto){
        return UUID.fromString(placeProto.getPlaceId());
    }

    public static UUID getPlaceByPlaceIdProtoToUUID(PlaceProto placeProto){
        return UUID.fromString(placeProto.getPlaceId());
    }

    public static UUID findPlacesByContextIdProtoToUUID(PlaceProto placeProto){
        return UUID.fromString(placeProto.getContextId());
    }

    public static String findPlacesByNamesProtoToString(PlaceProto placeProto){
        return placeProto.getName();
    }

    public static String findPlacesByValueProtoToString(PlaceProto placeProto){
        return placeProto.getValue();
    }
}
