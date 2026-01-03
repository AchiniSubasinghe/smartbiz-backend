package com.smartbiz.repo;

import com.smartbiz.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepo extends JpaRepository<Customer, UUID> {
    List<Customer> findByBusinessId(UUID businessId);

    Optional<Customer> findByCustomerName(String customerName);
}
