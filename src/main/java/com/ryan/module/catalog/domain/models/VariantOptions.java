package com.ryan.module.catalog.domain.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "variant_options", schema = "CTL")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariantOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "variant_option_id")
    private UUID variantOptionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariants variant;

    @Column(name = "option_name", nullable = false, length = 50)
    private String optionName;   // size, color, storage...

    @Column(name = "option_value", nullable = false, length = 80)
    private String optionValue;
}
