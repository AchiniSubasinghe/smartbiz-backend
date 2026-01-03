package com.smartbiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OwnerRequestDto {
    @NotBlank(message = "Enter the owner name")
    private String ownerName;
    @NotBlank(message = "Enter owner Email")
    private String ownerEmail;
    @NotBlank(message = "Enter owner Password")
    private String ownerPassword;
    @NotBlank(message = "Enter owner contact number")
    private String ownerContactNo;
    private UUID subscriptionPlanId;
}
