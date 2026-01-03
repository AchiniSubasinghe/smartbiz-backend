package com.smartbiz.controller;

import com.smartbiz.dto.request.ExpenseRequestDto;
import com.smartbiz.dto.response.ExpenseResponseDto;
import com.smartbiz.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public ExpenseResponseDto create(@RequestBody @Valid ExpenseRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ExpenseResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ExpenseResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-business/{businessId}")
    public List<ExpenseResponseDto> getByBusinessId(@PathVariable UUID businessId) {
        return service.getByBusinessId(businessId);
    }

    @PutMapping("/{id}")
    public ExpenseResponseDto update(@PathVariable UUID id, @RequestBody @Valid ExpenseRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
