package com.example.instagram.service;

import com.example.instagram.dto.CreateUserRequestDto;
import com.example.instagram.enums.ResponseCode;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;

    public String signup(CreateUserRequestDto requestDto) {

        return "success";
    }

    public String emailCheck(String email) {
        if (userRepository.countByEmail(email) > 0) {
            return ResponseCode.DUPLICATED_EMAIL.getMessage();
        }
        return "";
    }
}
