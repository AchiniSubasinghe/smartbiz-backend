package com.smartbiz.service;

import com.smartbiz.dto.request.InvoiceRequestDto;
import com.smartbiz.dto.response.InvoiceResponseDto;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    InvoiceResponseDto create(InvoiceRequestDto dto);

    List<InvoiceResponseDto> getAll();

    InvoiceResponseDto getById(UUID id);

    InvoiceResponseDto getBySalesId(UUID salesId);

    InvoiceResponseDto update(UUID id, InvoiceRequestDto dto);

    void delete(UUID id);
}
