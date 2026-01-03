package com.smartbiz.service;

import com.smartbiz.dto.request.BusinessRequestDto;
import com.smartbiz.dto.response.BusinessResponseDto;

import java.util.List;
import java.util.UUID;

public interface BusinessService {
    BusinessResponseDto create(BusinessRequestDto dto);

    List<BusinessResponseDto> getAll();

    BusinessResponseDto getById(UUID id);

    BusinessResponseDto getByName(String name);

    BusinessResponseDto update(UUID id, BusinessRequestDto dto);

    void delete(UUID id);
}
