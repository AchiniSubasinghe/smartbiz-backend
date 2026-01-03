package com.smartbiz.service.impl;

import com.smartbiz.dto.request.SalesRequestDto;
import com.smartbiz.dto.response.SalesResponseDto;
import com.smartbiz.entity.Inventory;
import com.smartbiz.entity.Order;
import com.smartbiz.entity.Sales;
import com.smartbiz.repo.InventoryRepo;
import com.smartbiz.repo.OrderRepo;
import com.smartbiz.repo.SalesRepo;
import com.smartbiz.service.SalesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepo salesRepo;
    private final InventoryRepo inventoryRepo;
    private final OrderRepo orderRepo;

    public SalesServiceImpl(SalesRepo salesRepo, InventoryRepo inventoryRepo, OrderRepo orderRepo) {
        this.salesRepo = salesRepo;
        this.inventoryRepo = inventoryRepo;
        this.orderRepo = orderRepo;
    }

    @Override
    public SalesResponseDto create(SalesRequestDto dto) {
        Inventory inventory = inventoryRepo.findById(dto.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Sales sales = Sales.builder()
                .totalPrice(dto.getTotalPrice())
                .quantity(dto.getQuantity())
                .inventory(inventory)
                .order(order)
                .build();

        Sales saved = salesRepo.save(sales);
        return mapToResponse(saved);
    }

    @Override
    public List<SalesResponseDto> getAll() {
        return salesRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SalesResponseDto getById(UUID id) {
        Sales sales = salesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales not found"));
        return mapToResponse(sales);
    }

    @Override
    public List<SalesResponseDto> getByOrderId(UUID orderId) {
        return salesRepo.findByOrderId(orderId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesResponseDto> getByInventoryId(UUID inventoryId) {
        return salesRepo.findByInventoryId(inventoryId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SalesResponseDto update(UUID id, SalesRequestDto dto) {
        Sales sales = salesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales not found"));

        Inventory inventory = inventoryRepo.findById(dto.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        sales.setTotalPrice(dto.getTotalPrice());
        sales.setQuantity(dto.getQuantity());
        sales.setInventory(inventory);
        sales.setOrder(order);

        return mapToResponse(salesRepo.save(sales));
    }

    @Override
    public void delete(UUID id) {
        Sales sales = salesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sales not found"));
        salesRepo.delete(sales);
    }

    private SalesResponseDto mapToResponse(Sales sales) {
        return new SalesResponseDto(
                sales.getId(),
                sales.getTotalPrice(),
                sales.getQuantity(),
                sales.getInventory().getId(),
                sales.getOrder().getId());
    }
}
