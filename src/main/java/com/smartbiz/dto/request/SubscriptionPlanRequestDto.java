package com.smartbiz.dto.request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionPlanRequestDto {
    private String subscriptionName;
    private BigDecimal subscriptionPrice;
}
