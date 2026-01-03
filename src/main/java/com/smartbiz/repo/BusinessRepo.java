package com.smartbiz.repo;

import com.smartbiz.entity.Business;
import com.smartbiz.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepo extends JpaRepository<Business,UUID>{
    Optional<Business> findByBusinessName(String businessName);
}
