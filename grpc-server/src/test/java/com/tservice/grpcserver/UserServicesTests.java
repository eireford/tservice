package com.tservice.grpcserver;

import com.tservice.sbg.services.UserService;
import com.tservice.sbg.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {UserServiceImpl.class})
public class UserServicesTests {

    @Autowired
    private UserService userService;

    @Test
    public void smokeTest() {
        assertNotNull(userService);
    }

    @Test
    public void createUserTest() {
        userService.saveUser(   null);
    }

}
