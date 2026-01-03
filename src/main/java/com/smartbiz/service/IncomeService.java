package com.smartbiz.service;

import com.smartbiz.dto.request.IncomeRequestDto;
import com.smartbiz.dto.response.IncomeResponseDto;

import java.util.List;
import java.util.UUID;

public interface IncomeService {
    IncomeResponseDto create(IncomeRequestDto dto);

    List<IncomeResponseDto> getAll();

    IncomeResponseDto getById(UUID id);

    List<IncomeResponseDto> getByBusinessId(UUID businessId);

    IncomeResponseDto update(UUID id, IncomeRequestDto dto);

    void delete(UUID id);
}
