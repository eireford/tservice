package com.tservice.grpcserver;

import com.tservice.grpcserver.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {UserRepository.class})
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void smokeTest() {
        assertNotNull(userRepository);
    }
}
