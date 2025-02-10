package com.example.instagram.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    // User
    DUPLICATED_EMAIL(200, "M0001", "다른 계정에서 동일한 이메일 주소를 사용 중입니다.")
    ,SIGNUP_SUCCESS(200, "M0002", "회원가입에 성공하였습니다.")
    ,SIGNUP_FAIL(500, "M0003", "회원가입에 실패하였습니다. 다시 시도해주세요")


    // Common
    ,REQUIRED_PARAM(200, "C0001", "필수 값을 입력해주세요.")

    ;

    private final int statusCode;
    private final String responseCode;
    private final String message;
}
