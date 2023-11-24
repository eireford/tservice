package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.UserEntity;
import com.tservice.proto.user.*;


import java.util.UUID;

public class UserMapper {

    public static UserEntity protoToEntity(UserProto userProto) {
        return UserEntity.builder()
                .id(UUID.fromString(userProto.getId()))
                .username(userProto.getUsername())
                .email(userProto.getEmail())
                .firstName(userProto.getFirstName())
                .lastName(userProto.getLastName())
                .build();
    }
    public static UserProto entityToProto(UserEntity userEntity) {
        return UserProto.newBuilder()
                .setId(userEntity.getId().toString())
                .setUsername(userEntity.getUsername())
                .setEmail(userEntity.getEmail())
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .build();
    }

    public static UserEntity createProtoToEntity(CreateProto createProto) {
        return UserEntity.builder()
                .username(createProto.getUsername())
                .email(createProto.getEmail())
                .firstName(createProto.getFirstName())
                .lastName(createProto.getLastName())
                .build();
    }

    public static UserEntity updateProtoToEntity(UpdateProto updateUserProto) {
        return UserEntity.builder()
                .id(UUID.fromString(updateUserProto.getId()))
                .username(updateUserProto.getUsername())
                .email(updateUserProto.getEmail())
                .firstName(updateUserProto.getFirstName())
                .lastName(updateUserProto.getLastName())
                .build();
    }

    public static UUID deleteProtoToUUID(DeleteProto deleteUserProto) {
        return UUID.fromString(deleteUserProto.getId());
    }

    public static UUID getByIdProtoToUUID(GetByIdProto getByIdProto) {
        return UUID.fromString(getByIdProto.getId());
    }

    public static String findByUsernameProtoToString(FindByUsernameProto findByUsernameProto) {
        return findByUsernameProto.getUsername();
    }

    public static String findByEmailProtoToString(FindByEmailProto findByEmailProto) {
        return findByEmailProto.getEmail();
    }

    public static String findByFirstNameProtoToString(FindByFirstNameProto findByFirstNameProto) {
        return findByFirstNameProto.getFirstName();
    }

    public static String findByLastNameProtoToString(FindByLastNameProto findByLastNameProto) {
        return findByLastNameProto.getLastName();
    }

}
