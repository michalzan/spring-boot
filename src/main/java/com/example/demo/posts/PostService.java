package com.example.demo.posts;

import com.example.demo.common.AppProperties;
import com.example.demo.users.model.User;
import com.example.demo.util.model.exceptions.UnprocessableContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {

    private final RestTemplate restTemplate;
    private final UserPostRepository repository;
    private final AppProperties properties;

    @Autowired
    public PostService(RestTemplate restTemplate, UserPostRepository repository, AppProperties properties) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.properties = properties;
    }

    public UserPostDto create(User user, String title, String body) {
        UserPostDto postDto = new UserPostDto();
        postDto.setTitle(title);
        postDto.setBody(body);
        postDto.setUserId(user.getPostsApiId());
        HttpEntity<UserPostDto> request = new HttpEntity<>(postDto);
        ResponseEntity<UserPostDto> response = restTemplate.postForEntity(properties.getJsonPlaceholderBaseUrl() + "/posts", request, UserPostDto.class);

        if (response.getStatusCode().is4xxClientError()) {
            throw new UnprocessableContentException("Server returned 4xx status code: " + response.getStatusCode());
        }
        if (response.getStatusCode().is5xxServerError()) {
            throw new UnprocessableContentException("Server returned 5xx status code: " + response.getStatusCode());
        }

        postDto = response.getBody();
        UserPostEntity entity = new UserPostEntity();
        entity.setPostId(postDto.getId());
        entity.setUser(user);
        repository.save(entity);

        return postDto;
    }

}
