package com.pjsdev.spring6restmvc.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BeerDTO {

    private UUID id;
    private Integer version;

    @NotNull
    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyle beerStyle;

    @NotNull
    @NotBlank
    private String upc;

    private Integer quantityOnHand;

    @NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
