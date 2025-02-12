package com.example.instagram.service;

import com.example.instagram.domain.User;
import com.example.instagram.dto.CreateUserRequestDto;
import com.example.instagram.dto.LoginRequestDto;
import com.example.instagram.dto.LoginResponseDto;
import com.example.instagram.enums.ResponseCode;
import com.example.instagram.jwt.JwtTokenProvider;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseCode signup(CreateUserRequestDto requestDto) {
        if (!StringUtils.hasText(requestDto.getEmail())
                || !StringUtils.hasText(requestDto.getPassword())
                || !StringUtils.hasText(requestDto.getUsername())
        ) {
            return ResponseCode.REQUIRED_PARAM;
        }

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        User user = userRepository.save(User.createUser(requestDto));

        if (user.getUserId() == null) {
            return ResponseCode.SIGNUP_FAIL;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUsername(),
                        requestDto.getPassword()
                )
        );

        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        // TODO: refresh token 생성하는 메서드
        String refreshToken = "";
        
        LoginResponseDto response = new LoginResponseDto(
                user.getUsername(), accessToken, refreshToken
        );

        return ResponseCode.SIGNUP_SUCCESS;
    }

    public String emailCheck(String email) {
        if (userRepository.countByEmail(email) > 0) {
            return ResponseCode.DUPLICATED_EMAIL.getMessage();
        }
        return "";
    }

    public void login(LoginRequestDto requestDto) {
    }

    public String usernameCheck(String username) {
        if (userRepository.countByUsername(username) > 0) {
            return ResponseCode.DUPLICATE_USERNAME.getMessage();
        }
        return "";
    }
}
