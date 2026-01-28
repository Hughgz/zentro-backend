package com.ryan.module.identity.domain.repositories;

import com.ryan.module.identity.domain.model.RefreshTokens;
import com.ryan.module.identity.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokens, UUID> {
    Boolean existsRefreshTokensByRefreshToken(String refreshToken);
    Optional<RefreshTokens> findRefreshTokensByUserAndRefreshToken(Users user, String refreshToken);
}
