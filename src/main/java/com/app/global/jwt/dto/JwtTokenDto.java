package com.app.global.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class JwtTokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Date accessTokenExpirationTime;
    private Date refreshTokenExpirationTime;
}
