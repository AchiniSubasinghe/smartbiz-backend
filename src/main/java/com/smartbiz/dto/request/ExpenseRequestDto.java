package com.smartbiz.dto.request;

import com.smartbiz.enums.ExpenseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ExpenseRequestDto {
    @NotBlank
    private String description;

    @NotNull
    private Double amount;

    @NotNull
    private LocalDate date;

    @NotNull
    private ExpenseType type;

}
