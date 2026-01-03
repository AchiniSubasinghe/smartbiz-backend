package com.smartbiz.service.impl;

import com.smartbiz.dto.request.ExpenseRequestDto;
import com.smartbiz.dto.response.ExpenseResponseDto;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Expense;
import com.smartbiz.repo.BusinessRepo;
import com.smartbiz.repo.ExpenseRepo;
import com.smartbiz.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepo expenseRepo;
    private final BusinessRepo businessRepo;

    @Override
    public ExpenseResponseDto create(ExpenseRequestDto dto) {
        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Expense expense = Expense.builder()
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .type(dto.getType())
                .business(business)
                .build();

        return ExpenseResponseDto.fromEntity(expenseRepo.save(expense));
    }

    @Override
    public List<ExpenseResponseDto> getAll() {
        return expenseRepo.findAll()
                .stream()
                .map(ExpenseResponseDto::fromEntity)
                .toList();
    }

    @Override
    public ExpenseResponseDto getById(UUID id) {
        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        return ExpenseResponseDto.fromEntity(expense);
    }

    @Override
    public List<ExpenseResponseDto> getByBusinessId(UUID businessId) {
        return expenseRepo.findByBusinessId(businessId)
                .stream()
                .map(ExpenseResponseDto::fromEntity)
                .toList();
    }

    @Override
    public ExpenseResponseDto update(UUID id, ExpenseRequestDto dto) {
        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setType(dto.getType());

        if (dto.getBusinessId() != null) {
            Business business = businessRepo.findById(dto.getBusinessId())
                    .orElseThrow(() -> new RuntimeException("Business not found"));
            expense.setBusiness(business);
        }

        return ExpenseResponseDto.fromEntity(expenseRepo.save(expense));
    }

    @Override
    public void delete(UUID id) {
        expenseRepo.deleteById(id);
    }
}
