package com.smartbiz.repo;

import com.smartbiz.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierRepo extends JpaRepository<Supplier, UUID> {
    List<Supplier> findByBusinessId(UUID businessId);

    Optional<Supplier> findBySupplierName(String supplierName);
}
