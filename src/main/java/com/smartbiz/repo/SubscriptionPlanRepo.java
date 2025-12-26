package com.smartbiz.repo;

import com.smartbiz.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionPlanRepo extends JpaRepository<SubscriptionPlan, UUID> {
    Optional<SubscriptionPlan> findBySubscriptionName(String subscriptionName);
}
