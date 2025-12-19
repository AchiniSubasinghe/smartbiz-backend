package com.smartbiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {

    @NotBlank(message = "Please enter your email")
    private String email;

    @NotBlank(message = "Please enter your password")
    private String password;



}
