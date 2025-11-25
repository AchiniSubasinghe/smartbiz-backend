package com.smartbiz.controller;

import com.smartbiz.entity.Product;
import com.smartbiz.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = productService.addProduct(product);
        return ResponseEntity.ok(saved);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Get product by SKU
    @GetMapping("/sku/{sku}")
    public ResponseEntity<Product> getProductBySku(@PathVariable String sku) {
        return ResponseEntity.ok(productService.getProductBySku(sku));
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable UUID id,
            @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
