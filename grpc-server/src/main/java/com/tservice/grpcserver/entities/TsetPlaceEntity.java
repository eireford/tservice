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
@Table(schema = "public", name = "tsets_places")
public class TsetPlaceEntity {

    @Id
    @Column(ID)
    private UUID id;

    @Column(TSET_ID)
    private UUID tsetId;

    @Column(PLACE_ID)
    private UUID placeId;

    public static final String ID = "tset_place_id";
    public static final String TSET_ID = "tset_id";
    public static final String PLACE_ID = "place_id";
}
