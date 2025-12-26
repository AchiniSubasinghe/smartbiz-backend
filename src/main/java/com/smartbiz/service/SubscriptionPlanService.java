package com.smartbiz.service;

import com.smartbiz.entity.SubscriptionPlan;

import java.util.List;
import java.util.UUID;

public interface SubscriptionPlanService {
    SubscriptionPlan create(SubscriptionPlan plan);
    List<SubscriptionPlan> getAll();
    SubscriptionPlan getById(UUID id);
    SubscriptionPlan getByName(String name);
    SubscriptionPlan update(UUID id, SubscriptionPlan plan);
    void delete(UUID id);
}
