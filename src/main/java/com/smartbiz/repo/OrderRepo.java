package com.smartbiz.repo;

import com.smartbiz.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, UUID> {
    List<Order> findByCustomerId(UUID customerId);

    List<Order> findByBusinessId(UUID businessId);
}
