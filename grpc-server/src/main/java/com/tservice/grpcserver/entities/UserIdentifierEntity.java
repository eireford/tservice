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
@Table(schema = "public", name = "user_identifier")
public class UserIdentifierEntity {

    @Id
    @Column(ID)
    private UUID uuid;

    @Column(USER_ID)
    private UUID userId;

    @Column(IDENTIFIER_ID)
    private UUID identifierId;

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String IDENTIFIER_ID = "identifier_id";
}
