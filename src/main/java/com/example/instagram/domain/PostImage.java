package com.example.instagram.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id", nullable = false)
    private Long id;

    @Column(name = "post_image_url", columnDefinition = "VARCHAR(300) COMMENT '이미지 경로'")
    private String postImageUrl;
}
