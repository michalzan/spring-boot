package com.example.demo.posts;

import lombok.Data;

@Data
public class UserPostDto {

    private Long id;
    private Long userId;
    private String title;
    private String body;

}
