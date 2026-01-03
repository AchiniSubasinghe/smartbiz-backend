package com.smartbiz.service.impl;

import com.smartbiz.dto.request.SupplierRequestDto;
import com.smartbiz.dto.response.SupplierResponseDto;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Supplier;
import com.smartbiz.repo.BusinessRepo;
import com.smartbiz.repo.SupplierRepo;
import com.smartbiz.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepo supplierRepo;
    private final BusinessRepo businessRepo;

    public SupplierServiceImpl(SupplierRepo supplierRepo, BusinessRepo businessRepo) {
        this.supplierRepo = supplierRepo;
        this.businessRepo = businessRepo;
    }

    @Override
    public SupplierResponseDto create(SupplierRequestDto dto) {
        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Supplier supplier = Supplier.builder()
                .supplierName(dto.getSupplierName())
                .supplierContactNo(dto.getSupplierContactNo())
                .business(business)
                .build();

        Supplier saved = supplierRepo.save(supplier);
        return mapToResponse(saved);
    }

    @Override
    public List<SupplierResponseDto> getAll() {
        return supplierRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDto getById(UUID id) {
        Supplier supplier = supplierRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        return mapToResponse(supplier);
    }

    @Override
    public SupplierResponseDto getByName(String name) {
        Supplier supplier = supplierRepo.findBySupplierName(name)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        return mapToResponse(supplier);
    }

    @Override
    public List<SupplierResponseDto> getByBusinessId(UUID businessId) {
        return supplierRepo.findByBusinessId(businessId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDto update(UUID id, SupplierRequestDto dto) {
        Supplier supplier = supplierRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Business business = businessRepo.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        supplier.setSupplierName(dto.getSupplierName());
        supplier.setSupplierContactNo(dto.getSupplierContactNo());
        supplier.setBusiness(business);

        return mapToResponse(supplierRepo.save(supplier));
    }

    @Override
    public void delete(UUID id) {
        Supplier supplier = supplierRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplierRepo.delete(supplier);
    }

    private SupplierResponseDto mapToResponse(Supplier supplier) {
        return new SupplierResponseDto(
                supplier.getId(),
                supplier.getSupplierName(),
                supplier.getSupplierContactNo(),
                supplier.getBusiness().getId());
    }
}
