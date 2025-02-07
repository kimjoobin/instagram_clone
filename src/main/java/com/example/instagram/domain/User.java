package com.example.instagram.domain;

import com.example.instagram.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "user_email")
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
}
