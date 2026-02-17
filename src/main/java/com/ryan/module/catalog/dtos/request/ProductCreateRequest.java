package com.ryan.module.catalog.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    @NotBlank(message = "Cannot blank name")
    private String name;
    private String description;
    private BigDecimal basePrice;
    @NotNull
    private UUID categoryId;
    @NotNull
    private UUID brandId;
}
