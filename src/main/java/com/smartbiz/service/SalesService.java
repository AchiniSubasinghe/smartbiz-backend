package com.smartbiz.service;

import com.smartbiz.dto.request.SalesRequestDto;
import com.smartbiz.dto.response.SalesResponseDto;

import java.util.List;
import java.util.UUID;

public interface SalesService {
    SalesResponseDto create(SalesRequestDto dto);

    List<SalesResponseDto> getAll();

    SalesResponseDto getById(UUID id);

    List<SalesResponseDto> getByOrderId(UUID orderId);

    List<SalesResponseDto> getByInventoryId(UUID inventoryId);

    SalesResponseDto update(UUID id, SalesRequestDto dto);

    void delete(UUID id);
}
