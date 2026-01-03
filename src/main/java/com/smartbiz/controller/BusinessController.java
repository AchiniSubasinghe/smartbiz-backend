package com.smartbiz.controller;

import com.smartbiz.dto.request.BusinessRequestDto;
import com.smartbiz.dto.response.BusinessResponseDto;
import com.smartbiz.service.BusinessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/businesses")
public class BusinessController {

    private final BusinessService service;

    public BusinessController(BusinessService service) {
        this.service = service;
    }

    @PostMapping
    public BusinessResponseDto create(@RequestBody BusinessRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<BusinessResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BusinessResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-name/{name}")
    public BusinessResponseDto getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PutMapping("/{id}")
    public BusinessResponseDto update(@PathVariable UUID id, @RequestBody BusinessRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
