package com.smartbiz.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class IncomeRequestDto {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private UUID businessId;
}
