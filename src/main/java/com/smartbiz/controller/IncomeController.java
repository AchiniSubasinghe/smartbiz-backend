package com.smartbiz.controller;

import com.smartbiz.dto.request.IncomeRequestDto;
import com.smartbiz.dto.response.IncomeResponseDto;
import com.smartbiz.service.IncomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService service;

    public IncomeController(IncomeService service) {
        this.service = service;
    }

    @PostMapping
    public IncomeResponseDto create(@RequestBody IncomeRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<IncomeResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public IncomeResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-business/{businessId}")
    public List<IncomeResponseDto> getByBusinessId(@PathVariable UUID businessId) {
        return service.getByBusinessId(businessId);
    }

    @PutMapping("/{id}")
    public IncomeResponseDto update(@PathVariable UUID id, @RequestBody IncomeRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
