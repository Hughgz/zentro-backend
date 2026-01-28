package com.ryan.module.identity.domain.model;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles {
    @EmbeddedId
    private UserRoleId id = new UserRoleId();
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Roles role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;


    @PrePersist
    void prePersist(){
        if(createdAt == null) {
            createdAt = Instant.now();
        }
    }
}
