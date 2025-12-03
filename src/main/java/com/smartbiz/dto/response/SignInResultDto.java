package com.smartbiz.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseCookie;

@Getter
@Setter
@Builder
public class SignInResultDto {
    private ResponseCookie cookie;
    private String token;
    private UserResponseDto userResponseDto;
}
