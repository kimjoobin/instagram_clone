package com.example.instagram.service;

import com.example.instagram.domain.User;
import com.example.instagram.dto.CreateUserRequestDto;
import com.example.instagram.enums.ResponseCode;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

        return ResponseCode.SIGNUP_SUCCESS;
    }

    public String emailCheck(String email) {
        if (userRepository.countByEmail(email) > 0) {
            return ResponseCode.DUPLICATED_EMAIL.getMessage();
        }
        return "";
    }

    public void login() {
    }
}
