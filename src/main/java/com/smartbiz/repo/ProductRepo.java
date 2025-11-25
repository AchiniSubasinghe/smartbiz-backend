package com.smartbiz.repo;

import com.smartbiz.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product , UUID> {

    Optional<Product> findBySku(String sku);
    List<Product> findBySkuStartingWithIgnoreCase(String sku);
    boolean existsBySku(String sku);
}
