package com.ryan.module.catalog.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "categories", schema = "CTL")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;
    private String name;
    private String slug;
    private UUID parentId;
}
