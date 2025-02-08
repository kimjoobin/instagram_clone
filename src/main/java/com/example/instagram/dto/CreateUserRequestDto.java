package com.example.instagram.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
    private String username;
    private String name;
    private String email;
    private String password;
}
