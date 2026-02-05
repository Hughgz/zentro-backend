package com.ryan.module.catalog.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "product_images", schema = "CTL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImages {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "image_id")
    private UUID imageId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(name = "is_primary", nullable = false)
    private Boolean primary = false;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;
}
