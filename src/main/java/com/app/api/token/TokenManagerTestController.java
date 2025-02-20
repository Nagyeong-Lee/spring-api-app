package com.app.api.token;

import com.app.domain.member.constant.Role;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token-test")
public class TokenManagerTestController {

    private final TokenManager tokenManager;

    @GetMapping("/create")
    public JwtTokenDto createToken() {
       return tokenManager.createJwtToken(1L, Role.USER);
    }

    @GetMapping("/valid")
    public String validToken(@RequestParam String token) {
        tokenManager.validToken(token);
        Claims claims = tokenManager.getTokenClaims(token);
        Long id = Long.parseLong(String.valueOf(claims.get("memberId")));
        String role = String.valueOf(claims.get("role"));
        log.info("id : {}, role : {}", id, role);
        return "SUCCESS";
    }

}
