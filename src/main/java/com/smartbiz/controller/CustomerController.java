package com.smartbiz.controller;

import com.smartbiz.dto.request.CustomerRequestDto;
import com.smartbiz.dto.response.CustomerResponseDto;
import com.smartbiz.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerResponseDto create(@RequestBody CustomerRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CustomerResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-name/{name}")
    public CustomerResponseDto getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("/by-business/{businessId}")
    public List<CustomerResponseDto> getByBusinessId(@PathVariable UUID businessId) {
        return service.getByBusinessId(businessId);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto update(@PathVariable UUID id, @RequestBody CustomerRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
