package com.smartbiz.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class InventoryRequestDto {
    private Integer quantity;
    private BigDecimal perUnitPrice;
    private UUID businessId;
    private UUID supplierId;
}
