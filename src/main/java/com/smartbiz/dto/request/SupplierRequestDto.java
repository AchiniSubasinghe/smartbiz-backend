package com.smartbiz.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SupplierRequestDto {
    private String supplierName;
    private String supplierContactNo;
    private UUID businessId;
}
