package com.ryan.module.catalog.domain.models;

import com.ryan.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product_variants", schema = "CTL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariants extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID variantId;
    @Column(name = "sku", nullable = false)
    private String sku;
    private BigDecimal price;
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;


    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VariantOptions> options = new HashSet<>();


}
