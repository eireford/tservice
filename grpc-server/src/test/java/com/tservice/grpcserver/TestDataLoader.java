package com.tservice.grpcserver;

import com.tservice.grpcserver.entities.UserEntity;
import com.tservice.grpcserver.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;

import java.util.UUID;
import java.util.function.Supplier;

//@AllArgsConstructor
//@SpringBootTest(classes = {GrpcServerApplication.class, UserServiceImpl.class})
public class TestDataLoader {

//    @Autowired
//    private final UserServiceImpl userService;
//
//    @Test
//    public void test() {
//        StringBuilder queryString = new StringBuilder();
//        userService.save(UserEntity.builder()
//                        .uuid(UUID.fromString("b0ccdd8f-b992-4dc8-8d2a-734c1420fa57"))
//                        .username("test")
//                        .email("test@test.com")
//                        .firstName("test")
//                        .lastName("test")
//                        .build());
//    }

}
