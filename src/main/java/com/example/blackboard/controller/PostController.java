package com.example.blackboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
