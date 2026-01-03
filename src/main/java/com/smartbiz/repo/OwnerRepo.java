package com.smartbiz.repo;

import com.smartbiz.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OwnerRepo extends JpaRepository<Owner, UUID> {
    Optional<Owner> findByOwnerName(String ownerName);
}
