package com.smartbiz.service;

import com.smartbiz.dto.request.ExpenseRequestDto;
import com.smartbiz.dto.response.ExpenseResponseDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    ExpenseResponseDto create(ExpenseRequestDto requestDto);
    List<ExpenseResponseDto> getAll();
    void delete (UUID id);
}
