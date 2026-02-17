package com.ryan.module.catalog.dtos.request;

import com.ryan.module.catalog.domain.models.Products;
import com.ryan.module.catalog.domain.models.VariantOptions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductVariantRequest {
    private String sku;
    private BigDecimal price;
    private Products product;
    private Set<VariantOptions> options = new HashSet<>();
}
