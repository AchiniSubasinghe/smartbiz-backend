    package com.smartbiz.service;

    import com.smartbiz.dto.request.SubscriptionPlanRequestDto;
    import com.smartbiz.dto.response.SubscriptionPlanResponseDto;
    import com.smartbiz.entity.SubscriptionPlan;

    import java.util.List;
    import java.util.UUID;

    public interface SubscriptionPlanService {
        SubscriptionPlanResponseDto create(SubscriptionPlanRequestDto plan);
        List<SubscriptionPlanResponseDto> getAll();
        SubscriptionPlanResponseDto getById(UUID id);
        SubscriptionPlanResponseDto getByName(String name);
        SubscriptionPlanResponseDto update(UUID id, SubscriptionPlanRequestDto plan);
        void delete(UUID id);
    }
