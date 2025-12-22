package com.smartbiz.dto.response;

import com.smartbiz.entity.Expense;
import com.smartbiz.enums.ExpenseType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Builder
public class ExpenseResponseDto {
    private UUID id;
    private String description;
    private double amount;
    private LocalDate date;
    private ExpenseType type;

    public static ExpenseResponseDto fromEntity (Expense expense){
        return ExpenseResponseDto.builder()
                .id(expense.getId())
                .description(expense.getDescription())
                .amount(expense.getAmount())
                .date(expense.getDate())
                .type(expense.getType())
                .build();
    }
}

