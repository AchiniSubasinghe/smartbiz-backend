package com.smartbiz.service.impl;

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
    public Product addProduct(Product product) {
        if (productRepo.existsBySku(product.getSku())) {
            throw new RuntimeException("SKU already exists");
        }
        return productRepo.save(product);
    }

    // Update Product
    @Override
    public Product updateProduct(UUID id, Product updatedDetails) {
        Product product = getProductById(id);

        product.setSku(updatedDetails.getSku());
        product.setName(updatedDetails.getName());
        product.setUnitPrice(updatedDetails.getUnitPrice());
        product.setQuantity(updatedDetails.getQuantity());

        return productRepo.save(product);
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
}
