package com.tservice.sbg.mappers;

import com.tservice.sbg.domain.User;
import com.tservice.api.CreateUserRequest;

public class UserMapper {

    public static User requestToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .build();

    }
}
