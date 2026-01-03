package com.smartbiz.service;

import com.smartbiz.dto.request.SupplierRequestDto;
import com.smartbiz.dto.response.SupplierResponseDto;

import java.util.List;
import java.util.UUID;

public interface SupplierService {
    SupplierResponseDto create(SupplierRequestDto dto);

    List<SupplierResponseDto> getAll();

    SupplierResponseDto getById(UUID id);

    SupplierResponseDto getByName(String name);

    List<SupplierResponseDto> getByBusinessId(UUID businessId);

    SupplierResponseDto update(UUID id, SupplierRequestDto dto);

    void delete(UUID id);
}
