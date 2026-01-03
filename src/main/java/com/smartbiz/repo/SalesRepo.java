package com.smartbiz.repo;

import com.smartbiz.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SalesRepo extends JpaRepository<Sales, UUID> {
    List<Sales> findByOrderId(UUID orderId);

    List<Sales> findByInventoryId(UUID inventoryId);
}
