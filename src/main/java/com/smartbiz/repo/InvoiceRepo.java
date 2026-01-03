package com.smartbiz.repo;

import com.smartbiz.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepo extends JpaRepository<Invoice, UUID> {
    Optional<Invoice> findBySalesId(UUID salesId);
}
