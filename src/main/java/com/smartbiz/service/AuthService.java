package com.smartbiz.service;


import com.smartbiz.dto.request.SignInRequestDto;
import com.smartbiz.dto.response.SignInResultDto;
import com.smartbiz.dto.request.SignUpRequestDto;
import com.smartbiz.dto.response.UserResponseDto;

public interface AuthService {
    public UserResponseDto addUser(SignUpRequestDto signUpRequestDto);
    public SignInResultDto signIn (SignInRequestDto signInRequestDto);
    public String sendResetEmail(String email);
    void resetPasswordWithToken(String token,String newPassword);


}
