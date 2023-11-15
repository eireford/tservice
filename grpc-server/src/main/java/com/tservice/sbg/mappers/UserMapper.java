package com.tservice.sbg.mappers;

import com.tservice.sbg.domain.User;
import com.tservice.proto.CreateUserRequest;
import com.tservice.proto.GetUserByUserIdRequest;

import java.util.UUID;

public class UserMapper {

    public static User requestToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .build();

    }

    public static UUID requestToUserId(GetUserByUserIdRequest getUserByUserIdRequest) {
        return UUID.fromString(getUserByUserIdRequest.getUserId());
    }
}
