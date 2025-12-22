package com.smartbiz.controller;

import com.smartbiz.dto.request.ExpenseRequestDto;
import com.smartbiz.dto.response.ExpenseResponseDto;
import com.smartbiz.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ExpenseResponseDto create(@RequestBody @Valid ExpenseRequestDto dto) {
        return expenseService.create(dto);
    }

    @GetMapping
    public List<ExpenseResponseDto> getAll() {
        return expenseService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        expenseService.delete(id);
    }
}
