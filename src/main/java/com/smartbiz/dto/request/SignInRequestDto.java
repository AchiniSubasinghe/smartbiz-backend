package com.smartbiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {

    @NotBlank(message = "UserName cannot be Null")
    private String username;

    @NotBlank(message = "Password cannot be Null")
    private String password;



}
