package com.smartbiz.service;

import com.smartbiz.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(UUID id,Product product);
    void deleteProduct(UUID id);
    Product getProductById(UUID id);
    Product getProductBySku(String sku);
    List<Product> getAllProducts();
}


