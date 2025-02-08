package com.example.instagram.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    // User
    DUPLICATED_EMAIL(200, "M0001", "다른 계정에서 동일한 이메일 주소를 사용 중입니다.")
    ,

    ;

    private final int statusCode;
    private final String responseCode;
    private final String message;
}
