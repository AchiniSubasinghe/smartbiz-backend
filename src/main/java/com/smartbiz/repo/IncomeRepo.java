package com.smartbiz.repo;

import com.smartbiz.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IncomeRepo extends JpaRepository<Income, UUID> {
    List<Income> findByBusinessId(UUID businessId);
}
