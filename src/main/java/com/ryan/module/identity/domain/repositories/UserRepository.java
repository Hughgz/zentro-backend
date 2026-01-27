package com.ryan.module.identity.domain.repositories;

import com.ryan.module.identity.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    boolean existsUsersByUsername(String username);
    boolean existsUsersByEmail(String email);
    boolean existsUsersByPhone(String phone);
    Optional<Users> findUsersByUsername(String username);

    @Query("""
    select u from Users u
    left join fetch u.userRoles ur
    left join fetch ur.role r
    where u.username = :username
""")
    Optional<Users> findByUsernameWithRoles(@Param("username") String username);

}
