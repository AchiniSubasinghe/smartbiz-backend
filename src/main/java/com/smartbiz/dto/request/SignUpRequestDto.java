package com.smartbiz.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter


public class SignUpRequestDto {
    @Size(min=6 ,message = "Username must have at least 6 characters")
    @NotBlank(message = "Username cannot be Null")
    private String username;

    @Size(min=8 ,message = "Password must have at least 8 characters")
    @NotBlank(message = "Password cannot be Null")
    private String password;

    @NotBlank(message = "Email cannot be Null")
    private String email;

//    @NotBlank(message = "Please select at least one role")
//    private String role;

    @NotBlank(message = "Please Enter your Full Name")
    private String fullName;

}
