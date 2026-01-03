package com.smartbiz.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequestDto {
    private LocalDate orderDate;
    private UUID customerId;
    private UUID businessId;
}
