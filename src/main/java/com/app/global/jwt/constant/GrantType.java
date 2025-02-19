package com.app.global.jwt.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrantType {
    BEARER("Bearer");

    private String type;
}
