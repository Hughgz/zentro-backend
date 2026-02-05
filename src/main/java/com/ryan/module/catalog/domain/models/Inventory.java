package com.ryan.module.catalog.domain.models;

import com.ryan.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.IntegerRange;

import java.time.Instant;
import java.util.UUID;

@Table(name = "inventory", schema = "CTL")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID inventoryId;
    @Column(name = "on_hand_qty", nullable = false)
    private Integer onHandQty = 0; //real quantity;
    @Column(name = "reversed_qty", nullable = false)
    private Integer reversedQty = 0; //number or booking place
    @Column(name = "low_stock_threshold", nullable = false)
    private Integer lowStockThreshold = 5;
    @Version
    @Column(name = "version")
    private Long version;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false, unique = true)
    private ProductVariants variant;

    public int getAvailableQty(){
        return Math.max(0, (onHandQty == null ? 0 : onHandQty) - (reversedQty == null ? 0 : reversedQty));
    }

}
