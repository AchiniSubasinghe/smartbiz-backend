package com.smartbiz.controller;

import com.smartbiz.dto.request.OrderRequestDto;
import com.smartbiz.dto.response.OrderResponseDto;
import com.smartbiz.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<OrderResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<OrderResponseDto> getByCustomerId(@PathVariable UUID customerId) {
        return service.getByCustomerId(customerId);
    }

    @GetMapping("/by-business/{businessId}")
    public List<OrderResponseDto> getByBusinessId(@PathVariable UUID businessId) {
        return service.getByBusinessId(businessId);
    }

    @PutMapping("/{id}")
    public OrderResponseDto update(@PathVariable UUID id, @RequestBody OrderRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
