package com.ryan.module.identity.domain.model;

import com.ryan.module.identity.shared.enums.RoleCodeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "roles")
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    private RoleCodeEnum code = RoleCodeEnum.USER;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UserRoles> userRoles;

    @Transient
    public Set<Roles> getRoles() {
        if (userRoles == null || userRoles.isEmpty()) return Collections.emptySet();
        return userRoles.stream()
                .map(UserRoles::getRole)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
