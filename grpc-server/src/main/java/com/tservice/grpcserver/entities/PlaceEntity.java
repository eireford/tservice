package com.tservice.grpcserver.entities;

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
@Table(schema = "public", name = "places")
public class PlaceEntity {

    @Id
    @Column(ID)
    private UUID id;

    @Length(max = 256, message = "The property 'name' must not exceed 256 characters.")
    @Column(NAME)
    private String name;

    @Length(max = 256, message = "The property 'value' must not exceed 256 characters.")
    @Column(VALUE)
    private String value;

    @Column(ROW)
    private int row;

    @Column(COL)
    private int col;

    public static final String ID = "place_id";
    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String ROW = "row";
    public static final String COL = "col";
}
