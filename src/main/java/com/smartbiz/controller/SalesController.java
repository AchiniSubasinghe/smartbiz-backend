package com.smartbiz.controller;

import com.smartbiz.dto.request.SalesRequestDto;
import com.smartbiz.dto.response.SalesResponseDto;
import com.smartbiz.service.SalesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService service;

    public SalesController(SalesService service) {
        this.service = service;
    }

    @PostMapping
    public SalesResponseDto create(@RequestBody SalesRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<SalesResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SalesResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-order/{orderId}")
    public List<SalesResponseDto> getByOrderId(@PathVariable UUID orderId) {
        return service.getByOrderId(orderId);
    }

    @GetMapping("/by-inventory/{inventoryId}")
    public List<SalesResponseDto> getByInventoryId(@PathVariable UUID inventoryId) {
        return service.getByInventoryId(inventoryId);
    }

    @PutMapping("/{id}")
    public SalesResponseDto update(@PathVariable UUID id, @RequestBody SalesRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
