package com.ryan.module.identity.domain.model;

import com.ryan.module.identity.shared.enums.RoleCodeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    private RoleCodeEnum code = RoleCodeEnum.USER;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private Set<UserRoles> userRoles;
}
