package com.smartbiz.service.impl;

import com.smartbiz.dto.request.IncomeRequestDto;
import com.smartbiz.dto.response.IncomeResponseDto;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Income;
import com.smartbiz.repo.BusinessRepo;
import com.smartbiz.repo.IncomeRepo;
import com.smartbiz.service.IncomeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepo incomeRepo;
    private final BusinessRepo businessRepo;

    public IncomeServiceImpl(IncomeRepo incomeRepo, BusinessRepo businessRepo) {
        this.incomeRepo = incomeRepo;
        this.businessRepo = businessRepo;
    }

    @Override
    public IncomeResponseDto create(IncomeRequestDto dto) {
        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Income income = Income.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .business(business)
                .build();

        Income saved = incomeRepo.save(income);
        return mapToResponse(saved);
    }

    @Override
    public List<IncomeResponseDto> getAll() {
        return incomeRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public IncomeResponseDto getById(UUID id) {
        Income income = incomeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));
        return mapToResponse(income);
    }

    @Override
    public List<IncomeResponseDto> getByBusinessId(UUID businessId) {
        return incomeRepo.findByBusinessId(businessId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public IncomeResponseDto update(UUID id, IncomeRequestDto dto) {
        Income income = incomeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        income.setDescription(dto.getDescription());
        income.setAmount(dto.getAmount());
        income.setDate(dto.getDate());
        income.setBusiness(business);

        return mapToResponse(incomeRepo.save(income));
    }

    @Override
    public void delete(UUID id) {
        Income income = incomeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));
        incomeRepo.delete(income);
    }

    private IncomeResponseDto mapToResponse(Income income) {
        return new IncomeResponseDto(
                income.getId(),
                income.getDescription(),
                income.getAmount(),
                income.getDate(),
                income.getBusiness().getId());
    }
}
