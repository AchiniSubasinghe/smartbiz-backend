package com.smartbiz.repo;

import com.smartbiz.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InventoryRepo extends JpaRepository<Inventory, UUID> {
    List<Inventory> findByBusinessId(UUID businessId);

    List<Inventory> findBySupplierId(UUID supplierId);
}
