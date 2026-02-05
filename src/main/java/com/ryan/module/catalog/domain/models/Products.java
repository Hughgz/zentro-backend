package com.ryan.module.catalog.domain.models;

import com.ryan.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Table(name = "products", schema = "CTL")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    private String name;
    private String slug;
    @Column(columnDefinition = "text")
    private String description;
    @Column(name = "base_price")
    private BigDecimal basePrice = BigDecimal.ZERO;
    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brands brand;




}
