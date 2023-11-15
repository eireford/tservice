package com.tservice.sbg.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Table(schema = "public", name = "tset")
public class Tset {
    @Id
    @Column(TSET_ID)
    private UUID tspoonId;

    @Column(CONTEXT_ID)
    private UUID contextId;

    @Size(max = 256, message = "The property 'name' must not exceed 256 characters.")
    @Column(NAME)
    private String name;

    @Size(max = 256, message = "The property 'value' must not exceed 256 characters.")
    @Column(VALUE)
    private String value;

    public static final String TSET_ID = "tset_id";
    public static final String CONTEXT_ID = "context_id";
    public static final String NAME = "name";
    public static final String VALUE = "value";
}
