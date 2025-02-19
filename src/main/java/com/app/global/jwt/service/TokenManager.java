package com.app.global.jwt.service;

import com.app.domain.member.constant.Role;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import com.app.global.jwt.constant.GrantType;
import com.app.global.jwt.constant.TokenType;
import com.app.global.jwt.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    //    토큰 생성, 검증
    private final String accessTokenExpirationTime;
    private final String refreshTokenExpirationTime;
    private final String secretKey;

    public JwtTokenDto createJwtToken(Long id, Role role) {

        //토큰 만료 시간 생성
        Date accessTokenExpirationTime = createAccessTokenExpirationTime();
        Date refreshTokenExpirationTime = createRefreshTokenExpirationTime();

        //토큰 생성
        String accessToken = createAccessToken(id, role, accessTokenExpirationTime);
        String refreshToken = createRefreshToken(id, refreshTokenExpirationTime);

        return JwtTokenDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpirationTime(accessTokenExpirationTime)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(refreshTokenExpirationTime)
                .build();
    }

    public Date createAccessTokenExpirationTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createRefreshTokenExpirationTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public String createAccessToken(Long id, Role role, Date expirationTime) {
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())
                .setIssuedAt(new Date())
                .setExpiration(expirationTime)
                .claim("role", role)
                .claim("memberId", id)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();

        return accessToken;
    }

    public String createRefreshToken(Long id, Date expirationDate) {
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .claim("memberId", id)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();

        return refreshToken;
    }

    public void validToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.error("expired token", e);
            throw new BusinessException(ErrorCode.EXPIRED_TOKEN);
        } catch (Exception e) {
            log.error("invalid token", e);
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
    }

    public Claims getTokenClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                         .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                         .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("token claim parse error", e);
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
        return claims;
    }

}
