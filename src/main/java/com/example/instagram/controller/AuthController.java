package com.example.instagram.controller;

import com.example.instagram.common.ApiResponse;
import com.example.instagram.dto.CreateUserRequestDto;
import com.example.instagram.dto.LoginRequestDto;
import com.example.instagram.enums.ResponseCode;
import com.example.instagram.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Log4j2
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signup(@RequestBody @Valid CreateUserRequestDto requestDto) {
        ResponseCode response = authService.signup(requestDto);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(ApiResponse.of(response));
    }

    @GetMapping("/email/check")
    public String emailCheck(@RequestParam String email) {
        return authService.emailCheck(email);
    }

    @GetMapping("/username/check")
    public String usernameCheck(@RequestParam String username) {
        return authService.usernameCheck(username);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto requestDto) {
        authService.login(requestDto);
    }
}
