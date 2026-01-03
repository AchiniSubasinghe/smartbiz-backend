package com.smartbiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponseDto {
    private UUID id;
    private String supplierName;
    private String supplierContactNo;
    private UUID businessId;
}
