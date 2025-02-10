package com.example.instagram.controller;

import com.example.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/stories")
    public void getStories() {
        postService.getStories();
    }

    @GetMapping("/posts")
    public void getPosts() {
        postService.getPosts();
    }
}
