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

public class SubscriptionPlanResponseDto {
    private UUID id;
    private String subscriptionName;
    private BigDecimal subscriptionPrice;
}
