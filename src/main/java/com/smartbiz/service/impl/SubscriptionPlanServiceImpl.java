package com.smartbiz.service.impl;

import com.smartbiz.dto.request.SubscriptionPlanRequestDto;
import com.smartbiz.dto.response.SubscriptionPlanResponseDto;
import com.smartbiz.entity.SubscriptionPlan;
import com.smartbiz.repo.SubscriptionPlanRepo;
import com.smartbiz.service.SubscriptionPlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {
    private final SubscriptionPlanRepo repository;

    public SubscriptionPlanServiceImpl(SubscriptionPlanRepo repository) {
        this.repository = repository;
    }

    @Override
    public SubscriptionPlanResponseDto create(SubscriptionPlanRequestDto dto) {
        SubscriptionPlan plan = new SubscriptionPlan();
        plan.setSubscriptionName(dto.getSubscriptionName());
        plan.setSubscriptionPrice(dto.getSubscriptionPrice());

        SubscriptionPlan saved = repository.save(plan);
        return mapToResponse(saved);
    }

    private SubscriptionPlanResponseDto mapToResponse(SubscriptionPlan saved) {
        return new SubscriptionPlanResponseDto(
                saved.getId(),
                saved.getSubscriptionName(),
                saved.getSubscriptionPrice()
        );
    }

    @Override
    public List<SubscriptionPlanResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionPlanResponseDto getById(UUID id) {
        SubscriptionPlan plan = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription Plan Id not found "));
        return mapToResponse(plan);
    }

    @Override
    public SubscriptionPlanResponseDto getByName(String name) {
        SubscriptionPlan plan = repository.findBySubscriptionName(name)
                .orElseThrow(() -> new RuntimeException("Subscription Plan not found"));
        return mapToResponse(plan);
    }

    @Override
    public SubscriptionPlanResponseDto update(UUID id, SubscriptionPlanRequestDto dto) {
        SubscriptionPlan plan = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription Plan Id not found"));

        plan.setSubscriptionName(dto.getSubscriptionName());
        plan.setSubscriptionPrice(dto.getSubscriptionPrice());

        return mapToResponse(repository.save(plan));
    }

    @Override
    public void delete(UUID id) {
        SubscriptionPlan plan = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription Plan not found"));
        repository.delete(plan);
    }
}
