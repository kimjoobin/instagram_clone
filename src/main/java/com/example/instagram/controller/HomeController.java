package com.example.instagram.controller;

import com.example.instagram.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/stories")
    public void getStories() {
        homeService.getStories();
    }

    @GetMapping("/posts")
    public void getPosts() {
        homeService.getPosts();
    }
}
