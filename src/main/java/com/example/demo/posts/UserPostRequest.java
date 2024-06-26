package com.example.demo.posts;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserPostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String body;

}
