package com.smartbiz.service;

import com.smartbiz.dto.request.OwnerRequestDto;
import com.smartbiz.dto.response.OwnerResponseDto;

import java.util.List;
import java.util.UUID;

public interface OwnerService {
    OwnerResponseDto create(OwnerRequestDto dto);

    List<OwnerResponseDto> getAll();

    OwnerResponseDto getById(UUID id);

    OwnerResponseDto getByName(String name);

    OwnerResponseDto update(UUID id, OwnerRequestDto dto);

    void delete(UUID id);
}
