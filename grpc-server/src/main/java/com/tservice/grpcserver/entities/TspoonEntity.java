package com.tservice.grpcserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Table(schema = "public", name = "tspoon")
public class TspoonEntity {

    @Id
    @Column(ID)
    private UUID uuid;

    @Size(max = 256, message = "The property 'name' must not exceed 256 characters.")
    @Column(NAME)
    private String name;

    @Size(max = 256, message = "The property 'value' must not exceed 256 characters.")
    @Column(VALUE)
    private String value;

    public static final String ID = "tspoon_id";
    public static final String NAME = "name";
    public static final String VALUE = "value";
}
