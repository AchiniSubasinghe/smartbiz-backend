package com.smartbiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaleRequestDto {
    @NotBlank(message = "Product stock keeping uint is requied")
    private String productSku;

    @NotNull(message = "Quantity required")
    private double quantity;
}
