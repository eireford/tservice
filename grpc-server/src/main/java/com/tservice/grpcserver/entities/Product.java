package com.tservice.sbg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public",name = "product")
public class Product {

    @Id
    @Getter
    private UUID id;

    @NotNull
    @Size(max = 256,message = "The property 'name' must not exceed 256 characters")
    private String name;

    @NotNull
    private double price;
}
