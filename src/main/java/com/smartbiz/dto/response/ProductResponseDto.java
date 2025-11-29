package com.smartbiz.dto.response;

import com.smartbiz.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDto {
    private String id;
    private String sku;
    private String name;
    private double unitPrice;
    private double quantity;
    private String createdAt;

    public static ProductResponseDto fromEntity(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId().toString())
                .sku(product.getSku())
                .name(product.getName())
                .unitPrice(product.getUnitPrice())
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt().toString())
                .build();
    }
}
