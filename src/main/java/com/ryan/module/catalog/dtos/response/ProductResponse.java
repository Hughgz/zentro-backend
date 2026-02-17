package com.ryan.module.catalog.dtos.response;

import com.ryan.module.catalog.domain.models.Brands;
import com.ryan.module.catalog.domain.models.Categories;
import com.ryan.module.catalog.dtos.request.BrandRequest;
import com.ryan.module.catalog.dtos.request.CategoryRequest;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ProductResponse {
    private UUID productId;
    private String name;
    private String slug;
    private String description;
    private BigDecimal basePrice;
    private Boolean isActive;
    private UUID categoryId;
    private UUID brandId;
}
