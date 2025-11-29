package com.smartbiz.controller;

import com.smartbiz.dto.request.ProductRequestDto;
import com.smartbiz.dto.response.ProductResponseDto;
import com.smartbiz.entity.Product;
import com.smartbiz.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ✅ Create Product
    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto dto) {
        return ResponseEntity.ok(productService.addProduct(dto));
    }

    // ✅ Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable UUID id,
            @RequestBody ProductRequestDto dto) {

        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }


    // ✅ Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // ✅ Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(ProductResponseDto.fromEntity(product));
    }

    // ✅ Get Product by SKU
    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductResponseDto> getProductBySku(@PathVariable String sku) {
        Product product = productService.getProductBySku(sku);
        return ResponseEntity.ok(ProductResponseDto.fromEntity(product));
    }

    // ✅ Get all products
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> dtos = products.stream()
                .map(ProductResponseDto::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
