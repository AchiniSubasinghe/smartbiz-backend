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

//handle the api request and return json response
@RestController

//use the root directly POST PUT GET or DELETE
@RequestMapping()

//allow sending cookies from back end to front end(putting bcz back end and frontend
// running in different ports)
@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)

public class AuthController {
    //dependancy injection
    private final AuthService authService;

    //constructor injection
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//Regsiter
    @PostMapping("/signUp")
//    frontend send http post request as json body
    public ResponseEntity<ApiResponseDto<UserResponseDto>>
//    @Request converts json into signup request dto
//    @Valid checks validation rules
    signUp(@RequestBody @Valid SignUpRequestDto dto) {
//  Controller sends DTO to AuthService
        UserResponseDto response = authService.addUser(dto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "User registered successfully", response));
    }

//LogIN
    @PostMapping("/signIn")
    public ResponseEntity<ApiResponseDto<SignInResultDto>>
    signIn(@RequestBody @Valid SignInRequestDto dto) {
        SignInResultDto response = authService.signIn(dto);
        return ResponseEntity.ok(new ApiResponseDto<>(true, "Login successful", response));
    }

//Log Out
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
