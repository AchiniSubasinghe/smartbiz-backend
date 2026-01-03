package com.smartbiz.service.impl;

import com.smartbiz.dto.request.BusinessRequestDto;
import com.smartbiz.dto.response.BusinessResponseDto;
import com.smartbiz.entity.Business;
import com.smartbiz.repo.BusinessRepo;
import com.smartbiz.service.BusinessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {
    private final BusinessRepo businessRepo;

    public BusinessServiceImpl(BusinessRepo businessRepo) {
        this.businessRepo = businessRepo;
    }

    @Override
    public BusinessResponseDto create(BusinessRequestDto dto) {
        Business business = new Business();
        business.setBusinessName(dto.getBusinessName());
        business.setBusinessCategory(dto.getBusinessCategory());
        Business saved = businessRepo.save(business);
        return mapToResponse(saved);
    }

    @Override
    public List<BusinessResponseDto> getAll() {
        return businessRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BusinessResponseDto getById(UUID id) {
        Business business = businessRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        return mapToResponse(business);
    }

    @Override
    public BusinessResponseDto getByName(String name) {
        Business business = businessRepo.findByBusinessName(name)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        return mapToResponse(business);
    }

    @Override
    public BusinessResponseDto update(UUID id, BusinessRequestDto dto) {
        Business business = businessRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        business.setBusinessName(dto.getBusinessName());
        business.setBusinessCategory(dto.getBusinessCategory());
        return mapToResponse(businessRepo.save(business));
    }

    @Override
    public void delete(UUID id) {
        Business business = businessRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        businessRepo.delete(business);
    }

    private BusinessResponseDto mapToResponse(Business business) {
        return BusinessResponseDto.builder()
                .id(business.getId())
                .businessName(business.getBusinessName())
                .businessCategory(business.getBusinessCategory())
                .build();
    }
}
