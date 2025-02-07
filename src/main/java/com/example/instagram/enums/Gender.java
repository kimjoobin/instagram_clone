package com.example.instagram.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남성")
    ,FEMALE("여성")
    ,PRIVATE("밝히고 싶지 않음")
    ;

    private final String desc;

    Gender(String desc) {
        this.desc = desc;
    }
}
