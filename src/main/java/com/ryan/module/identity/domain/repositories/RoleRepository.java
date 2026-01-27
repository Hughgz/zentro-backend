package com.ryan.module.identity.domain.repositories;


import com.ryan.module.identity.domain.model.Roles;
import com.ryan.module.identity.shared.enums.RoleCodeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Roles, UUID> {
    Optional<Roles> findRolesByCode(RoleCodeEnum code);
}
