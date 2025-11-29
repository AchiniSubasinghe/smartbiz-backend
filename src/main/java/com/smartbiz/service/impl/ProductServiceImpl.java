package com.smartbiz.service.impl;

import com.smartbiz.dto.request.ProductRequestDto;
import com.smartbiz.dto.response.ProductResponseDto;
import com.smartbiz.entity.Product;
import com.smartbiz.repo.ProductRepo;
import com.smartbiz.service.ProductService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // Create Product
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        if (productRepo.existsBySku(productRequestDto.getSku())) {
            throw new RuntimeException("SKU already exists");
        }
        Product product = Product.builder()
                .sku(productRequestDto.getSku())
                .name(productRequestDto.getName())
                .unitPrice(productRequestDto.getUnitPrice())
                .quantity(productRequestDto.getQuantity())
                .build();

        return ProductResponseDto.fromEntity(productRepo.save(product));
    }

    // Update Product
    public ProductResponseDto updateProduct(UUID id, ProductRequestDto dto) {

        Product product = getProductById(id);

        // Prevent duplicate SKU (except its own)
        if (!product.getSku().equals(dto.getSku()) &&
                productRepo.existsBySku(dto.getSku())) {
            throw new RuntimeException("SKU already exists");
        }

        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setUnitPrice(dto.getUnitPrice());
        product.setQuantity(dto.getQuantity());

        productRepo.save(product);

        return ProductResponseDto.fromEntity(product);
    }


    // Delete Product
    @Override
    public void deleteProduct(UUID id) {
        Product product = getProductById(id);
        productRepo.delete(product);
    }

    // Get by ID
    @Override
    public Product getProductById(UUID id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Get by SKU
    @Override
    public Product getProductBySku(String sku) {
        return productRepo.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product not found by SKU"));
    }

    // Get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

     //    Get low stock
    @Override
    public List<Product> getLowStockProducts() {
        double lowStock =10;
        return productRepo.findByQuantityLessThan(lowStock);
    }


}
