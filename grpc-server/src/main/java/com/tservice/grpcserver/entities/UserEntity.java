package com.tservice.grpcserver.entities;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Table(schema = "public", name = "users")
public class UserEntity {

    @Id
    @Column(ID)
    private UUID id;

    @Length(max = 256, message = "The property 'username' must not exceed 256 characters.")
    @Column(USERNAME)
    private String username;

    @Email(message = "The property 'email' must be a valid email address.")
    @Column(EMAIL)
    private String email;

    @Length(max = 256, message = "The property 'first name' must not exceed 256 characters.")
    @Column(FIRST_NAME)
    private String firstName;

    @Length(max = 256, message = "The property 'last name' must not exceed 256 characters.")
    @Column(LAST_NAME)
    private String lastName;

    public static final String ID = "user_id";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
}
