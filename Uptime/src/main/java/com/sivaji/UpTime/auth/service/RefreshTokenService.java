package com.sivaji.UpTime.auth.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sivaji.UpTime.auth.entity.RefreshToken;
import com.sivaji.UpTime.auth.entity.User;
import com.sivaji.UpTime.auth.repository.RefreshTokenRepository;
import com.sivaji.UpTime.common.exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    // CREATE REFRESH TOKEN
    public RefreshToken createRefreshToken(User user, String token) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(token);
        refreshToken.setUser(user);

        refreshToken.setCreatedAt(LocalDateTime.now());

        refreshToken.setExpiryDate(
                LocalDateTime.now().plusSeconds(refreshTokenExpiration / 1000)
        );

        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(refreshToken);
    }

    // VERIFY REFRESH TOKEN
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new BadRequestException("Invalid refresh token"));

        if (refreshToken.isRevoked()) {
            throw new BadRequestException("Refresh token revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Refresh token expired");
        }

        return refreshToken;
    }

    // REVOKE TOKEN (FOR LOGOUT)
    public void revokeToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new BadRequestException("Invalid refresh token"));

        refreshToken.setRevoked(true);

        refreshTokenRepository.save(refreshToken);
    }
}