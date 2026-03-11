package com.sivaji.UpTime.auth.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sivaji.UpTime.auth.dto.AuthResponseDto;
import com.sivaji.UpTime.auth.dto.LoginDto;
import com.sivaji.UpTime.auth.dto.RefreshTokenDto;
import com.sivaji.UpTime.auth.dto.RegisterDto;
import com.sivaji.UpTime.auth.service.AuthService;
import com.sivaji.UpTime.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Test endpoint
    @GetMapping
    public String getMessage() {
        return "Hello from Auth Controller";
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(
            @Valid @RequestBody RegisterDto request) {

        authService.register(request);

        ApiResponse<?> response = new ApiResponse<>(
                true,
                "User registered successfully",
                null,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(
            @Valid @RequestBody LoginDto request) {

        AuthResponseDto tokens = authService.login(request);

        ApiResponse<AuthResponseDto> response = new ApiResponse<>(
                true,
                "Login successful",
                tokens,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    // REFRESH ACCESS TOKEN
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponseDto>> refreshToken(
            @Valid @RequestBody RefreshTokenDto request) {

        AuthResponseDto tokens = authService.refreshToken(request);

        ApiResponse<AuthResponseDto> response = new ApiResponse<>(
                true,
                "Token refreshed successfully",
                tokens,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(
            @Valid @RequestBody RefreshTokenDto request) {

        authService.logout(request);

        ApiResponse<?> response = new ApiResponse<>(
                true,
                "Logout successful",
                null,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }
}