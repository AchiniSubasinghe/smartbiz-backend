package com.smartbiz.service.impl;

import com.smartbiz.entity.SubscriptionPlan;
import com.smartbiz.repo.SubscriptionPlanRepo;
import com.smartbiz.service.SubscriptionPlanService;

import java.util.List;
import java.util.UUID;

public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
    private final SubscriptionPlanRepo repository;

    public SubscriptionPlanServiceImpl(SubscriptionPlanRepo repository) {
        this.repository = repository;
    }

    @Override
    public SubscriptionPlan create(SubscriptionPlan plan) {
        return repository.save(plan);
    }

    @Override
    public List<SubscriptionPlan> getAll() {
        return repository.findAll();
    }

    @Override
    public SubscriptionPlan getById(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Subscription Plan ID not found"));
    }

    @Override
    public SubscriptionPlan getByName(String name) {
        return repository.findBySubscriptionName(name)
                .orElseThrow(() -> new RuntimeException("Subscription Plan Name not found"));
    }

    @Override
    public SubscriptionPlan update(UUID id, SubscriptionPlan plan) {
        SubscriptionPlan existing = getById(id);
        existing.setSubscriptionName(plan.getSubscriptionName());
        existing.setSubscriptionPrice(plan.getSubscriptionPrice());
        return repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        SubscriptionPlan plan = getById(id);
        repository.delete(plan);
    }
}
