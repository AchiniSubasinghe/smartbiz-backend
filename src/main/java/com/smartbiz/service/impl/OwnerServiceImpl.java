package com.smartbiz.service.impl;

import com.smartbiz.dto.request.OwnerRequestDto;
import com.smartbiz.dto.response.OwnerResponseDto;
import com.smartbiz.entity.Owner;
import com.smartbiz.entity.SubscriptionPlan;
import com.smartbiz.repo.OwnerRepo;
import com.smartbiz.repo.SubscriptionPlanRepo;
import com.smartbiz.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepo ownerRepo;
    private final SubscriptionPlanRepo subscriptionPlanRepo;

    public OwnerServiceImpl(OwnerRepo ownerRepo, SubscriptionPlanRepo subscriptionPlanRepo) {
        this.ownerRepo = ownerRepo;
        this.subscriptionPlanRepo = subscriptionPlanRepo;
    }

    @Override
    public OwnerResponseDto create(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setOwnerName(dto.getOwnerName());
        owner.setOwnerEmail(dto.getOwnerEmail());
        owner.setOwnerPassword(dto.getOwnerPassword());
        owner.setOwnerContactNo(dto.getOwnerContactNo());

        if (dto.getSubscriptionPlanId() != null) {
            SubscriptionPlan plan = subscriptionPlanRepo.findById(dto.getSubscriptionPlanId())
                    .orElseThrow(() -> new RuntimeException("Subscription Plan not found"));
            owner.setSubscriptionPlan(plan);
        }

        Owner saved = ownerRepo.save(owner);
        return mapToResponse(saved);
    }

    @Override
    public List<OwnerResponseDto> getAll() {
        return ownerRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerResponseDto getById(UUID id) {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        return mapToResponse(owner);
    }

    @Override
    public OwnerResponseDto getByName(String name) {
        Owner owner = ownerRepo.findByOwnerName(name)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        return mapToResponse(owner);
    }

    @Override
    public OwnerResponseDto update(UUID id, OwnerRequestDto dto) {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        owner.setOwnerName(dto.getOwnerName());
        owner.setOwnerEmail(dto.getOwnerEmail());
        owner.setOwnerPassword(dto.getOwnerPassword());
        owner.setOwnerContactNo(dto.getOwnerContactNo());

        if (dto.getSubscriptionPlanId() != null) {
            SubscriptionPlan plan = subscriptionPlanRepo.findById(dto.getSubscriptionPlanId())
                    .orElseThrow(() -> new RuntimeException("Subscription Plan not found"));
            owner.setSubscriptionPlan(plan);
        }

        return mapToResponse(ownerRepo.save(owner));
    }

    @Override
    public void delete(UUID id) {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        ownerRepo.delete(owner);
    }

    private OwnerResponseDto mapToResponse(Owner owner) {
        return OwnerResponseDto.builder()
                .id(owner.getId())
                .ownerName(owner.getOwnerName())
                .ownerEmail(owner.getOwnerEmail())
                .ownerPassword(owner.getOwnerPassword())
                .ownerContactNo(owner.getOwnerContactNo())
                .build();
    }
}
