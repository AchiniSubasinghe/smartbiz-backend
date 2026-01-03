package com.smartbiz.controller;

import com.smartbiz.dto.request.OwnerRequestDto;
import com.smartbiz.dto.response.OwnerResponseDto;
import com.smartbiz.service.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @PostMapping
    public OwnerResponseDto create(@RequestBody OwnerRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<OwnerResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OwnerResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-name/{name}")
    public OwnerResponseDto getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PutMapping("/{id}")
    public OwnerResponseDto update(@PathVariable UUID id, @RequestBody OwnerRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
