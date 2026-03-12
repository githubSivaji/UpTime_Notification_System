package com.sivaji.UpTime.auth.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sivaji.UpTime.auth.dto.AuthResponseDto;
import com.sivaji.UpTime.auth.dto.LoginDto;
import com.sivaji.UpTime.auth.dto.RefreshTokenDto;
import com.sivaji.UpTime.auth.dto.RegisterDto;
import com.sivaji.UpTime.auth.entity.RefreshToken;
import com.sivaji.UpTime.auth.entity.User;
import com.sivaji.UpTime.auth.repository.UserRepository;
import com.sivaji.UpTime.auth.security.jwt.JwtService;
import com.sivaji.UpTime.common.exception.BadRequestException;
import com.sivaji.UpTime.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;


    // REGISTER
    public void register(RegisterDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }


    // LOGIN
    public AuthResponseDto login(LoginDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String accessToken = jwtService.generateAccessToken(user.getId());
        String refreshToken = jwtService.generateRefreshToken(user.getId());

        refreshTokenService.createRefreshToken(user, refreshToken);

        return new AuthResponseDto(accessToken, refreshToken);
    }


    // REFRESH ACCESS TOKEN
    public AuthResponseDto refreshToken(RefreshTokenDto request) {

        RefreshToken refreshToken =
                refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        Long userId = jwtService.extractUserId(refreshToken.getToken());

        String newAccessToken = jwtService.generateAccessToken(userId);

        return new AuthResponseDto(newAccessToken, refreshToken.getToken());
    }     
       
    // LOGOUT
    public void logout(RefreshTokenDto request) {

        refreshTokenService.revokeToken(request.getRefreshToken());
    }

}