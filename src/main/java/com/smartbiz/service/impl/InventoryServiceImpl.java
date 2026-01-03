package com.smartbiz.service.impl;

import com.smartbiz.dto.request.InventoryRequestDto;
import com.smartbiz.dto.response.InventoryResponseDto;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Inventory;
import com.smartbiz.entity.Supplier;
import com.smartbiz.repo.BusinessRepo;
import com.smartbiz.repo.InventoryRepo;
import com.smartbiz.repo.SupplierRepo;
import com.smartbiz.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepo inventoryRepo;
    private final BusinessRepo businessRepo;
    private final SupplierRepo supplierRepo;

    public InventoryServiceImpl(InventoryRepo inventoryRepo, BusinessRepo businessRepo, SupplierRepo supplierRepo) {
        this.inventoryRepo = inventoryRepo;
        this.businessRepo = businessRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public InventoryResponseDto create(InventoryRequestDto dto) {
        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Inventory inventory = Inventory.builder()
                .quantity(dto.getQuantity())
                .perUnitPrice(dto.getPerUnitPrice())
                .business(business)
                .supplier(supplier)
                .build();

        Inventory saved = inventoryRepo.save(inventory);
        return mapToResponse(saved);
    }

    @Override
    public List<InventoryResponseDto> getAll() {
        return inventoryRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponseDto getById(UUID id) {
        Inventory inventory = inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        return mapToResponse(inventory);
    }

    @Override
    public List<InventoryResponseDto> getByBusinessId(UUID businessId) {
        return inventoryRepo.findByBusinessId(businessId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryResponseDto> getBySupplierId(UUID supplierId) {
        return inventoryRepo.findBySupplierId(supplierId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponseDto update(UUID id, InventoryRequestDto dto) {
        Inventory inventory = inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        inventory.setQuantity(dto.getQuantity());
        inventory.setPerUnitPrice(dto.getPerUnitPrice());
        inventory.setBusiness(business);
        inventory.setSupplier(supplier);

        return mapToResponse(inventoryRepo.save(inventory));
    }

    @Override
    public void delete(UUID id) {
        Inventory inventory = inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventoryRepo.delete(inventory);
    }

    private InventoryResponseDto mapToResponse(Inventory inventory) {
        return new InventoryResponseDto(
                inventory.getId(),
                inventory.getQuantity(),
                inventory.getPerUnitPrice(),
                inventory.getBusiness().getId(),
                inventory.getSupplier().getId());
    }
}
