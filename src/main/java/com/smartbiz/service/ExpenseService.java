package com.smartbiz.service;

import com.smartbiz.dto.request.ExpenseRequestDto;
import com.smartbiz.dto.response.ExpenseResponseDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    ExpenseResponseDto create(ExpenseRequestDto dto);

    List<ExpenseResponseDto> getAll();

    ExpenseResponseDto getById(UUID id);

    List<ExpenseResponseDto> getByBusinessId(UUID businessId);

    ExpenseResponseDto update(UUID id, ExpenseRequestDto dto);

    void delete(UUID id);
}
