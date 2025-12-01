package com.smartbiz.controller;

import com.smartbiz.dto.request.SaleRequestDto;
import com.smartbiz.dto.response.SaleResponseDto;
import com.smartbiz.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDto> createSale(@RequestBody @Valid SaleRequestDto dto) {
        return ResponseEntity.ok(saleService.createSale(dto));
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> getAllSales() {
        return ResponseEntity.ok(
                saleService.getAllSales().stream()
                        .map(SaleResponseDto::fromEntity)
                        .toList()
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                SaleResponseDto.fromEntity(saleService.getSaleById(id))
        );
    }

    @GetMapping("/range")
    public ResponseEntity<List<SaleResponseDto>> getSalesInRange(
            @RequestParam String start,
            @RequestParam String end
    ) {
        LocalDateTime s = LocalDateTime.parse(start);
        LocalDateTime e = LocalDateTime.parse(end);

        return ResponseEntity.ok(
                saleService.getSalesBetween(s, e).stream()
                        .map(SaleResponseDto::fromEntity)
                        .toList()
        );
    }
}
