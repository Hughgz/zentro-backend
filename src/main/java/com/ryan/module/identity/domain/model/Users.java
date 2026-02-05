package com.ryan.module.identity.domain.model;

import com.ryan.common.model.BaseEntity;
import com.ryan.module.identity.shared.enums.RoleCodeEnum;
import com.ryan.module.identity.shared.enums.SexEnum;
import com.ryan.module.identity.shared.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_user_email", columnList = "email"),
        @Index(name = "idx_user_username", columnList = "username"),
        @Index(name = "idx_user_phone", columnList = "phone")
}, schema = "IDT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "middle_name", length = 50)
    private String middleName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "username", nullable = false, length = 100, unique = true)
    private String username;
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    private LocalDate dob;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "email_verify")
    private Boolean emailVerify;
    @Column(name = "phone_verify", nullable = false)
    private Boolean phoneVerify;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Addresses> address;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRoles> userRoles = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RefreshTokens> refreshTokens;

    @Transient
    public Set<RoleCodeEnum> getRoleCodes() {
        if (userRoles == null || userRoles.isEmpty()) return Collections.emptySet();
        return userRoles.stream()
                .map(UserRoles::getRole)
                .filter(Objects::nonNull)
                .map(Roles::getCode)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleCodes().stream()
                .map(Enum::name)
                .map(code -> code.startsWith("ROLE_") ? code : "ROLE_" + code)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public @Nullable String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != StatusEnum.BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return status == StatusEnum.ACTIVE;
    }
}
