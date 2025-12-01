package com.smartbiz.dto.response;

import com.smartbiz.entity.Sale;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter @Builder
public class SaleResponseDto {
    private UUID id;
    private String invoiceNumber;
    private String productSku;
    private String productName;
    private double quantity;
    private double unitPrice;
    private double totalAmount;
    private String createdAt;

    public static SaleResponseDto fromEntity(Sale sale) {
        return SaleResponseDto.builder()
                .id(sale.getId())
                .invoiceNumber(sale.getInvoiceNumber())
                .productSku(sale.getProductSku())
                .productName(sale.getProductName())
                .quantity(sale.getQuantity())
                .unitPrice(sale.getUnitPrice())
                .totalAmount(sale.getTotalAmount())
                .createdAt(sale.getCreatedAt().toString())
                .build();
    }
}
