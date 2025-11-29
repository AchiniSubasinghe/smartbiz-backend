package com.smartbiz.service;

import com.smartbiz.dto.request.ProductRequestDto;
import com.smartbiz.dto.response.ProductResponseDto;
import com.smartbiz.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto dto);
    ProductResponseDto updateProduct(UUID id, ProductRequestDto dto);
    void deleteProduct(UUID id);
    Product getProductById(UUID id);
    Product getProductBySku(String sku);
    List<Product> getAllProducts();
    List<Product>getLowStockProducts();

}


