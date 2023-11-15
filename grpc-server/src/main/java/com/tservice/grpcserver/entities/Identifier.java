package com.tservice.sbg.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class Identifier {

    @Id
    @Column(IDENTIFIER_ID)
    private UUID identifierId;

    @Column(CONTEXT_ID)
    private UUID contextId;

    @Column(USER_ID)
    private UUID userId;

    @Size(max = 256, message = "The property 'name' must not exceed 256 characters.")
    @Column(NAME)
    private String name;
    @Size(max = 256, message = "The property 'value' must not exceed 256 characters.")
    @Column(VALUE)
    private String value;

    public static final String IDENTIFIER_ID = "identifier_id";
    public static final String CONTEXT_ID = "context_id";
    public static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String VALUE = "value";


}
