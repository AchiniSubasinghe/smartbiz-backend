package com.smartbiz.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BusinessRequestDto {
    @NotBlank(message = "Enter the business name")
    private String businessName;
    @NotBlank(message = "Enter business catergory")
    private String businessCategory;
}
