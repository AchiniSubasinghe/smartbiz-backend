package com.smartbiz.controller;

import com.smartbiz.dto.request.InvoiceRequestDto;
import com.smartbiz.dto.response.InvoiceResponseDto;
import com.smartbiz.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    public InvoiceResponseDto create(@RequestBody InvoiceRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<InvoiceResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public InvoiceResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-sales/{salesId}")
    public InvoiceResponseDto getBySalesId(@PathVariable UUID salesId) {
        return service.getBySalesId(salesId);
    }

    @PutMapping("/{id}")
    public InvoiceResponseDto update(@PathVariable UUID id, @RequestBody InvoiceRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
