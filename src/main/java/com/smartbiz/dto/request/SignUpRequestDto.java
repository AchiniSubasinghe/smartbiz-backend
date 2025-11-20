package com.smartbiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter


public class SignUpRequestDto {
    @Size(min=6 ,message = "Username must have at least 6 characters")
    @NotBlank(message = "UserName cannot be Null")
    private String userName;

    @Size(min=8 ,message = "Password must have at least 8 characters")
    private String password;

    private String role;

    @NotBlank(message = "Please Enter your Full Name")
    private String fullName;

}
