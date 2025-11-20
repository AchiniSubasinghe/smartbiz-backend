package com.smartbiz.controller;

import com.smartbiz.dto.request.SignInRequestDto;
import com.smartbiz.dto.request.SignUpRequestDto;
import com.smartbiz.dto.response.ApiResponseDto;
import com.smartbiz.dto.response.SignInResultDto;
import com.smartbiz.dto.response.UserResponseDto;
import com.smartbiz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@CrossOrigin(
        origins = "http://localhost:5174",
        allowCredentials = "true"
)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> signUp(@RequestBody @Valid SignUpRequestDto dto) {
        UserResponseDto response = authService.addUser(dto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "User registered successfully", response));
    }

    @PostMapping("/signing")
    public ResponseEntity<ApiResponseDto<SignInResultDto>> signIng(@RequestBody @Valid SignInRequestDto dto) {
        SignInResultDto response = authService.signIn(dto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Login successful", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponseDto<Object>> logout() {
        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                .path("/")
                .maxAge(0)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body(new ApiResponseDto<>(true, "Logged out successfully", null));
    }
}
