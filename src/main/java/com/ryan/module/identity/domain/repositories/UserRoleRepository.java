package com.ryan.module.identity.domain.repositories;

import com.ryan.module.identity.domain.model.UserRoleId;
import com.ryan.module.identity.domain.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, UserRoleId> {
}
