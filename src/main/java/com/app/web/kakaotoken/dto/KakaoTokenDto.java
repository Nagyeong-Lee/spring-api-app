package com.app.web.kakaotoken.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class KakaoTokenDto {

    @Getter
    @Builder
    public static class Request {
        private String grant_type;
        private String client_id;
        private String client_secret;
        private String redirect_uri;
        private String code;
    }

    @Getter
    @ToString
    public static class Response {
        private String token_type;
        private String access_token;
        private String expires_in;
        private String refresh_token;
        private String refresh_token_expires_in;
    }
}
