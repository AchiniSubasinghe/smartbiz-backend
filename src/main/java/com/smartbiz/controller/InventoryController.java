package com.smartbiz.controller;

import com.smartbiz.dto.request.InventoryRequestDto;
import com.smartbiz.dto.response.InventoryResponseDto;
import com.smartbiz.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping
    public InventoryResponseDto create(@RequestBody InventoryRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<InventoryResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public InventoryResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-business/{businessId}")
    public List<InventoryResponseDto> getByBusinessId(@PathVariable UUID businessId) {
        return service.getByBusinessId(businessId);
    }

    @GetMapping("/by-supplier/{supplierId}")
    public List<InventoryResponseDto> getBySupplierId(@PathVariable UUID supplierId) {
        return service.getBySupplierId(supplierId);
    }

    @PutMapping("/{id}")
    public InventoryResponseDto update(@PathVariable UUID id, @RequestBody InventoryRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
