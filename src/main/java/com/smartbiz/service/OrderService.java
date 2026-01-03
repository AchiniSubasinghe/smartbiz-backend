package com.smartbiz.service;

import com.smartbiz.dto.request.OrderRequestDto;
import com.smartbiz.dto.response.OrderResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponseDto create(OrderRequestDto dto);

    List<OrderResponseDto> getAll();

    OrderResponseDto getById(UUID id);

    List<OrderResponseDto> getByCustomerId(UUID customerId);

    List<OrderResponseDto> getByBusinessId(UUID businessId);

    OrderResponseDto update(UUID id, OrderRequestDto dto);

    void delete(UUID id);
}
