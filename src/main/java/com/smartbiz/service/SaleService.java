package com.smartbiz.service;

import com.smartbiz.dto.request.SaleRequestDto;
import com.smartbiz.dto.response.SaleResponseDto;
import com.smartbiz.entity.Sale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SaleService {
    SaleResponseDto createSale(SaleRequestDto dto);
    List<Sale> getAllSales();
    Sale getSaleById(UUID id);
    List<Sale> getSalesBetween(LocalDateTime start, LocalDateTime end);
}
