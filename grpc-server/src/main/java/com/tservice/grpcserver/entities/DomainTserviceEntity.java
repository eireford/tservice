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
@Table(schema = "public", name = "domain_tservice")
public class DomainTserviceEntity {

    @Id
    @Column(ID)
    private UUID uuid;

    @Column(DOMAIN_ID)
    private UUID domainId;

    @Column(TSERVICE_ID)
    private UUID tserviceId;

    public static final String ID = "id";
    public static final String DOMAIN_ID = "domain_id";
    public static final String TSERVICE_ID = "tservice_id";
}
