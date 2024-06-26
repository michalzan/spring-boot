package com.example.demo.posts;

import com.example.demo.users.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user_post")
public class UserPostEntity {

    @Id
    private Long postId;

    @ManyToOne
    @JoinColumn(name="app_user_id")
    private User user;

}
