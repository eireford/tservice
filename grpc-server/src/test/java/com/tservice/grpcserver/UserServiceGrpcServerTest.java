package com.tservice.grpcserver;
import com.tservice.grpcserver.grpc.UserServiceGrpcServer;
import com.tservice.grpcserver.mappers.UserMapper;
import com.tservice.grpcserver.services.UserServiceImpl;
import com.tservice.proto.*;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.Mockito.*;

class UserServiceGrpcServerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserServiceGrpcServer grpcServer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Mock data
        CreateUserProto createUserProto = CreateUserProto.newBuilder()
                .setUsername("testUser")
                .setEmail("test@example.com")
                .setFirstName("John")
                .setLastName("Doe")
                .build();

        // Mock userService.saveUser to return a Mono<User>
        when(userService.saveUser(any())).thenReturn(Mono.just(UserMapper.createUserProtoToUser(createUserProto)));

        // Create a StreamObserver
        StreamObserver<UserProto> streamObserver = mock(StreamObserver.class);

        // Call the method to be tested
        grpcServer.createUser(createUserProto, streamObserver);

        // Verify that userService.saveUser was called
        verify(userService, times(1)).saveUser(any());

        // Verify that the onNext method was called on the streamObserver
        verify(streamObserver, times(1)).onNext(any());

        // Verify that the onCompleted method was called on the streamObserver
        verify(streamObserver, times(1)).onCompleted();

        // You may need to add more verifications based on your specific logic
    }

    // Similar tests can be written for other methods

    // Make sure to handle exceptions and edge cases in your tests
}
