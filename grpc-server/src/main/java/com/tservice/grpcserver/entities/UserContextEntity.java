package com.tservice.grpcserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Table(schema = "public", name = "users_contexts")
public class UserContextEntity {

    @Id
    @Column(ID)
    private UUID id;

    @Column(USER_ID)
    private UUID userId;

    @Column(CONTEXT_ID)
    private UUID contextId;

    public static final String ID = "user_context_id";
    public static final String USER_ID = "user_id";
    public static final String CONTEXT_ID = "context_id";
}
