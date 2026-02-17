package com.ryan.module.catalog.domain.repositories;

import com.ryan.module.catalog.domain.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<Products, UUID> {
}
