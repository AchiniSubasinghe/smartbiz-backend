package com.smartbiz.repo;

import com.smartbiz.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SaleRepo extends JpaRepository<Sale, UUID> {
    List<Sale> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Sale> findByProductSku(String sku);

}
