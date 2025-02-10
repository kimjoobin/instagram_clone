package com.example.instagram.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
    @NotBlank
    private String username;

    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
