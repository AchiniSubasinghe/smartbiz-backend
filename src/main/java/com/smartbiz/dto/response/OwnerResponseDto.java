package com.smartbiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OwnerResponseDto {
    private UUID id;
    private String ownerName;
    private String ownerEmail;
    private String ownerPassword;
    private String ownerContactNo;

}
