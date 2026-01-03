package com.smartbiz.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SalesRequestDto {
    private BigDecimal totalPrice;
    private Integer quantity;
    private UUID inventoryId;
    private UUID orderId;
}
