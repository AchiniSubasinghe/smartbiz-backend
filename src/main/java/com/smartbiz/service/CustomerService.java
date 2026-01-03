package com.smartbiz.service;

import com.smartbiz.dto.request.CustomerRequestDto;
import com.smartbiz.dto.response.CustomerResponseDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponseDto create(CustomerRequestDto dto);

    List<CustomerResponseDto> getAll();

    CustomerResponseDto getById(UUID id);

    CustomerResponseDto getByName(String name);

    List<CustomerResponseDto> getByBusinessId(UUID businessId);

    CustomerResponseDto update(UUID id, CustomerRequestDto dto);

    void delete(UUID id);
}
