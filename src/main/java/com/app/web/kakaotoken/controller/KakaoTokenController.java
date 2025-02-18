package com.app.web.kakaotoken.controller;

import com.app.web.kakaotoken.client.KakaoTokenClient;
import com.app.web.kakaotoken.dto.KakaoTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoTokenController {
    private final KakaoTokenClient kakaoTokenClient;
    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @ResponseBody
    @GetMapping("/oauth/kakao/callback")
    public String kakaoCallback(String code) {
        log.info("/// 인가코드 : {} ///", code);

        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        KakaoTokenDto.Request request = KakaoTokenDto.Request.builder()
                .grant_type("authorization_code")
                .client_id(clientId)
                .client_secret(clientSecret)
                .redirect_uri("http://localhost:8080/oauth/kakao/callback")
                .code(code)
                .build();

        KakaoTokenDto.Response response = kakaoTokenClient.requestKakaoToken(contentType, request);
        return "kakao token : " + response;
    }
}
