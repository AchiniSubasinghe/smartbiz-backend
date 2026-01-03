package com.smartbiz.service;

import com.smartbiz.dto.request.InventoryRequestDto;
import com.smartbiz.dto.response.InventoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface InventoryService {
    InventoryResponseDto create(InventoryRequestDto dto);

    List<InventoryResponseDto> getAll();

    InventoryResponseDto getById(UUID id);

    List<InventoryResponseDto> getByBusinessId(UUID businessId);

    List<InventoryResponseDto> getBySupplierId(UUID supplierId);

    InventoryResponseDto update(UUID id, InventoryRequestDto dto);

    void delete(UUID id);
}
