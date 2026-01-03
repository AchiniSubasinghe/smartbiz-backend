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
public class InventoryResponseDto {
    private UUID id;
    private Integer quantity;
    private BigDecimal perUnitPrice;
    private UUID businessId;
    private UUID supplierId;
}
