package com.ryan.module.identity.domain.repositories;

import com.ryan.module.identity.domain.model.RefreshTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokens, UUID> {
}
