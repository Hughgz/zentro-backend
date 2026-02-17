package com.ryan.module.catalog.domain.repositories;

import com.ryan.module.catalog.domain.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICategoryRepository extends JpaRepository<Categories, UUID> {
}
