package com.smartbiz.controller;

import com.smartbiz.dto.request.RestEmailRequestDto;
import com.smartbiz.dto.request.SignInRequestDto;
import com.smartbiz.dto.request.SignUpRequestDto;
import com.smartbiz.dto.response.ApiResponseDto;
import com.smartbiz.dto.response.RestPasswordConfirmDto;
import com.smartbiz.dto.response.SignInResultDto;
import com.smartbiz.dto.response.UserResponseDto;
import com.smartbiz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// handle the api request and return json response
@RestController
// use the root directly POST PUT GET or DELETE
@RequestMapping()
// allow sending cookies from back end to front end(putting bcz back end and frontend
// running in different ports)
@CrossOrigin(
        origins = "http://localhost:3000",
        allowCredentials = "true"
)
public class AuthController {

    // dependency injection
    private final AuthService authService;

    // constructor injection
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register
    @PostMapping("/signUp")
    // frontend send http post request as json body
    public ResponseEntity<ApiResponseDto<UserResponseDto>>
    // @Request converts json into signup request dto
    // @Valid checks validation rules
    signUp(@RequestBody @Valid SignUpRequestDto dto) {

        // Controller sends DTO to AuthService
        UserResponseDto response = authService.addUser(dto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "User registered successfully", response));
    }

    // Login
    @PostMapping("/signIn")
    public ResponseEntity<ApiResponseDto<SignInResultDto>> signIn(@RequestBody @Valid SignInRequestDto dto) {
        SignInResultDto response = authService.signIn(dto);
        ApiResponseDto<SignInResultDto> body = new ApiResponseDto<>(true, "Login successful", response);

        if (response.getCookie() != null) {
            // include Set-Cookie header so browser receives httpOnly cookie
            return ResponseEntity.ok()
                    .header("Set-Cookie", response.getCookie().toString())
                    .body(body);
        } else {
            return ResponseEntity.ok(body);
        }
    }

    // send reset email
    @PostMapping("/send-reset-email")
    public ResponseEntity<ApiResponseDto<Object>> sendRestEmail(@RequestBody RestEmailRequestDto restEmailRequestDto) {
        authService.sendResetEmail(restEmailRequestDto.getEmail());
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Reset link send to your email", null));
    }

    // reset password confirm
    @PostMapping("/reset-password-confirm")
    public ResponseEntity<ApiResponseDto<Object>> resetPassword(@RequestBody @Valid RestPasswordConfirmDto restPasswordConfirmDto) {

        if (!restPasswordConfirmDto.getNewPassword().equals(restPasswordConfirmDto.getConfirmPassword())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseDto<>(false, "Passwords do not match", null));
        }

        try {
            authService.resetPasswordWithToken(restPasswordConfirmDto.getToken(), restPasswordConfirmDto.getNewPassword());

            return ResponseEntity.ok(
                    new ApiResponseDto<>(true, "Password reset successfully", null));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponseDto<>(false, e.getMessage(), null)
            );
        }
    }

    // Log Out
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
