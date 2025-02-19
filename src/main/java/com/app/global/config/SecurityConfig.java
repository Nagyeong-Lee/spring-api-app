package com.app.global.config;

import com.app.global.jwt.service.TokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Value("${jwt.access-token-expire-time}")
    private String accessTokenExpirationTime;

    @Value("${jwt.refresh-token-expire-time}")
    private String refreshTokenExpirationTime;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public TokenManager tokenManager() {
        return new TokenManager(accessTokenExpirationTime, refreshTokenExpirationTime, secretKey);
    }
}
