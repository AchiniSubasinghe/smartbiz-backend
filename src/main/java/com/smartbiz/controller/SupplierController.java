package com.smartbiz.controller;

import com.smartbiz.dto.request.SupplierRequestDto;
import com.smartbiz.dto.response.SupplierResponseDto;
import com.smartbiz.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping
    public SupplierResponseDto create(@RequestBody SupplierRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<SupplierResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SupplierResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-name/{name}")
    public SupplierResponseDto getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("/by-business/{businessId}")
    public List<SupplierResponseDto> getByBusinessId(@PathVariable UUID businessId) {
        return service.getByBusinessId(businessId);
    }

    @PutMapping("/{id}")
    public SupplierResponseDto update(@PathVariable UUID id, @RequestBody SupplierRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
