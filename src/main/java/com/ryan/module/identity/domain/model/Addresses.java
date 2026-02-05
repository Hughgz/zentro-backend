package com.ryan.module.identity.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "address", indexes = {
        @Index(name = "idx_address_user_id", columnList = "user_id")
}, schema = "IDT")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    private UUID addressId;
    @Column(name = "receiver_name", nullable = false)
    private String receiverName;
    @Column(name = "receiver_phone", nullable = false)
    private String receiverPhone;
    @Column(name = "line_1", nullable = false)
    private String line1;
    @Column(name = "line_2")
    private String line2;
    @Column(nullable = false)
    private String ward;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String city;
    private String country;
    @Column(name = "postal_code", length = 20)
    private String postalCode;
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @PrePersist
    void prePersist(){
        if(country.isBlank() || country == null){
            country = "Vietnam";
        }
    }
}
