package com.smartbiz.service.impl;

import com.smartbiz.dto.request.SaleRequestDto;
import com.smartbiz.dto.response.SaleResponseDto;
import com.smartbiz.entity.Product;
import com.smartbiz.entity.Sale;
import com.smartbiz.repo.ProductRepo;
import com.smartbiz.repo.SaleRepo;
import com.smartbiz.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class SaleServiceImpl implements SaleService {
    private final SaleRepo saleRepo;
    private final ProductRepo productRepo;

    @Override
    public SaleResponseDto createSale(SaleRequestDto dto) {

        // 1. Find product
        Product product = productRepo.findBySku(dto.getProductSku())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. Check stock
        if (dto.getQuantity() > product.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        // 3. Reduce stock
        product.setQuantity(product.getQuantity() - dto.getQuantity());
        productRepo.save(product);

        // 4. Create sale
        Sale sale = Sale.builder()
                .invoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8))
                .productSku(product.getSku())
                .productName(product.getName())
                .quantity(dto.getQuantity())
                .unitPrice(product.getUnitPrice())
                .totalAmount(product.getUnitPrice() * dto.getQuantity())
                .build();

        saleRepo.save(sale);

        return SaleResponseDto.fromEntity(sale);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepo.findAll();
    }

    @Override
    public Sale getSaleById(UUID id) {
        return saleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @Override
    public List<Sale> getSalesBetween(LocalDateTime start, LocalDateTime end) {
        return saleRepo.findByCreatedAtBetween(start, end);
    }

}
