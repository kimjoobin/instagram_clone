package com.example.instagram.domain;

import com.example.instagram.domain.base.BaseTimeEntity;
import com.example.instagram.dto.CreateUserRequestDto;
import com.example.instagram.enums.Gender;
import com.example.instagram.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", nullable = false, unique = true, columnDefinition = "VARCHAR(20) COMMENT '사용자 이름(닉네임)'")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "name", columnDefinition = "VARCHAR(50) COMMENT '사용자 실명'")
    private String name;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_website")
    private String website;

    @Column(name = "user_following")
    private int following;

    @Column(name = "user_followers")
    private int followers;

    @Column(name = "user_introduce")
    private String introduce;

    @Column(name = "user_profile_img")
    private String profileImg;

    @Column(name = "user_phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gender")
    private Gender gender;

    public static User createUser(CreateUserRequestDto requestDto) {
        return User.builder()
                .username(requestDto.getUsername())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .gender(Gender.PRIVATE)
                .role(UserRole.ROLE_USER)
                .build();
    }
}
