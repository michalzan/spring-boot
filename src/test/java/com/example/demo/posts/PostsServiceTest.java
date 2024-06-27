package com.example.demo.posts;

import com.example.demo.common.AppProperties;
import com.example.demo.users.model.User;
import com.example.demo.util.model.exceptions.ServerError;
import com.example.demo.util.model.exceptions.UnprocessableContentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostsServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private UserPostRepository repository;
    @Autowired
    private AppProperties properties;

    private PostService postService;

    @BeforeEach
    public void setUp() {
        postService = new PostService(restTemplate, repository, properties);
    }

    @Test
    public void createPostTest() {
        User user = new User();
        user.setPostsApiId(1L);

        UserPostDto userPostDto = new UserPostDto();
        userPostDto.setTitle("test title");
        userPostDto.setBody("test body");
        userPostDto.setId(1L);

        UserPostEntity entity = new UserPostEntity();
        entity.setPostId(userPostDto.getId());
        entity.setUser(user);
        when(restTemplate
                .postForEntity(any(String.class), any(HttpEntity.class), any()))
                .thenReturn(new ResponseEntity<>(userPostDto, HttpStatusCode.valueOf(200)));
        when(repository.save(any())).thenReturn(entity);

        UserPostDto returned = postService.create(user, "test title", "test body");
        assertEquals(userPostDto, returned);
        verify(restTemplate, times(1)).postForEntity(any(String.class), any(HttpEntity.class), any());
        verify(repository, times(1)).save(any());


        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        assertThrows(
                UnprocessableContentException.class,
                () -> postService.create(user, "test title", "test body"),
                "Should throw UnprocessableContentException"
        );

        when(restTemplate.postForEntity(any(String.class), any(HttpEntity.class), any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThrows(
                ServerError.class,
                () -> postService.create(user, "test title", "test body"),
                "Should throw UnprocessableContentException"
        );
    }



}
