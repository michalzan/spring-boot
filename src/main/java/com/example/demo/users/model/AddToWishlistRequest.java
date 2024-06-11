package com.example.demo.users.model;

import lombok.Data;

import java.util.List;

@Data
public class AddToWishlistRequest {

    private List<String> itemIds;

}
