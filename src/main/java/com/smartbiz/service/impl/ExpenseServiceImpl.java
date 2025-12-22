package com.smartbiz.service.impl;

import com.smartbiz.dto.request.ExpenseRequestDto;
import com.smartbiz.dto.response.ExpenseResponseDto;
import com.smartbiz.entity.Expense;
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

    @Override
    public ExpenseResponseDto create(ExpenseRequestDto requestDto){
        Expense expense = Expense.builder()
                .description(requestDto.getDescription())
                .amount(requestDto.getAmount())
                .date(requestDto.getDate())
                .type(requestDto.getType())
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
    public void delete(UUID id) {
        expenseRepo.deleteById(id);
    }
}
