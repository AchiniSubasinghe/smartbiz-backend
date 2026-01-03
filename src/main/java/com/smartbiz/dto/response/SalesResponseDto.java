package com.smartbiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesResponseDto {
    private UUID id;
    private BigDecimal totalPrice;
    private Integer quantity;
    private UUID inventoryId;
    private UUID orderId;
}
