package com.ryan.module.catalog.mappers;

import com.ryan.module.catalog.domain.models.Products;
import com.ryan.module.catalog.dtos.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse toResponse(Products product) {
        ProductResponse res = new ProductResponse();
        res.setProductId(product.getProductId());
        res.setName(product.getName());
        res.setSlug(product.getSlug());
        res.setDescription(product.getDescription());
        res.setBasePrice(product.getBasePrice());
        res.setIsActive(product.getIsActive());
        res.setCategoryId(product.getCategory().getCategoryId());
        res.setBrandId(product.getBrand().getBrandId());
        return res;
    }
}
