package com.tservice.grpcserver.mappers;

import com.tservice.grpcserver.entities.User;
import com.tservice.proto.*;


import java.util.UUID;

public class UserMapper {

    public static User createUserProtoToUser(CreateUserProto createUserProto) {
        return null;
    }

    public static User userProtoToUserEntity(UserProto userProto) {
        return User.builder()
                .userId(UUID.fromString(userProto.getUserId()))
                .username(userProto.getUsername())
                .email(userProto.getEmail())
                .firstName(userProto.getFirstName())
                .lastName(userProto.getLastName())
                .build();
    }
    public static UserProto UserEntityToUserProto(User userEntity) {
        return UserProto.newBuilder()
                .setUserId(userEntity.getUserId().toString())
                .setUsername(userEntity.getUsername())
                .setEmail(userEntity.getEmail())
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .build();
    }

    public static User createUserProtoToUserEntity(CreateUserProto createUserProto) {
        return User.builder()
                .username(createUserProto.getUsername())
                .email(createUserProto.getEmail())
                .firstName(createUserProto.getFirstName())
                .lastName(createUserProto.getLastName())
                .build();
    }

    public static User updateUserProtoToUserEntity(UpdateUserProto updateUserProto) {
        return User.builder()
                .userId(UUID.fromString(updateUserProto.getUserId()))
                .username(updateUserProto.getUsername())
                .email(updateUserProto.getEmail())
                .firstName(updateUserProto.getFirstName())
                .lastName(updateUserProto.getLastName())
                .build();
    }

    public static UUID deleteUserProtoToUUID(DeleteUserProto deleteUserProto) {
        return UUID.fromString(deleteUserProto.getUserId());
    }

    public static UUID getUserByUserIdProtoToUUID(GetUserByUserIdProto getUserByUserIdProto) {
        return UUID.fromString(getUserByUserIdProto.getUserId());
    }

    public static UUID findUsersByContextIdProtoToUUID(FindUsersByContextIdProto findUsersByContextIdProto) {
        return UUID.fromString(findUsersByContextIdProto.getContextId());
    }

    public static String findUsersByUsernameProtoToString(FindUsersByUsernameProto findUsersByUsernameProto) {
        return findUsersByUsernameProto.getUsername();
    }

    public static String findUsersByEmailProtoToString(FindUsersByEmailProto findUsersByEmailProto) {
        return findUsersByEmailProto.getEmail();
    }

    public static String findUsersByFirstNameProtoToString(FindUsersByFirstNameProto findUsersByFirstNameProto) {
        return findUsersByFirstNameProto.getFirstName();
    }

    public static String findUsersByLastNameProtoToString(FindUsersByLastNameProto findUsersByLastNameProto) {
        return findUsersByLastNameProto.getLastName();
    }

}
