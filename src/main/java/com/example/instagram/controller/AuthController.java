package com.example.instagram.controller;

import com.example.instagram.dto.CreateUserRequestDto;
import com.example.instagram.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(CreateUserRequestDto requestDto) {
        authService.signup(requestDto);
    }

    @GetMapping("/email/check")
    public String emailCheck(@RequestParam String email) {
        return authService.emailCheck(email);
    }
}
