package com.ryan.module.identity.domain.repositories;

import com.ryan.module.identity.domain.model.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Addresses, UUID> {
    List<Addresses> findAddressesByUserUserId(UUID userId);
}
