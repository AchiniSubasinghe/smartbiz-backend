package com.smartbiz.controller;

import com.smartbiz.dto.request.SubscriptionPlanRequestDto;
import com.smartbiz.dto.response.SubscriptionPlanResponseDto;
import com.smartbiz.service.SubscriptionPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subscription-plans")
public class SubscriptionPlanController {
    private final SubscriptionPlanService service;

    public SubscriptionPlanController(SubscriptionPlanService service) {
        this.service = service;
    }

    @PostMapping
    public SubscriptionPlanResponseDto create(
            @RequestBody SubscriptionPlanRequestDto dto
    ) {
        return service.create(dto);
    }

    @GetMapping
    public List<SubscriptionPlanResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SubscriptionPlanResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-name/{name}")
    public SubscriptionPlanResponseDto getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PutMapping("/{id}")
    public SubscriptionPlanResponseDto update(
            @PathVariable UUID id,
            @RequestBody SubscriptionPlanRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}

