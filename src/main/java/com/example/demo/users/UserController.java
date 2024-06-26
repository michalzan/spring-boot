package com.example.demo.users;

import com.example.demo.common.AppProperties;
import com.example.demo.posts.PostService;
import com.example.demo.posts.UserPostDto;
import com.example.demo.posts.UserPostRequest;
import com.example.demo.users.model.AddToWishlistRequest;
import com.example.demo.users.model.User;
import com.example.demo.util.model.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    private final UserService service;
    private final AppProperties properties;
    private final PostService postService;

    @Autowired
    public UserController(UserService service, PostService postService, AppProperties properties) {
        this.service = service;
        this.properties = properties;
        this.postService = postService;
    }

    @Operation(
            description = "This endpoint greets the user."
    )
    @GetMapping("hello")
    public String hello(){
        logger.log(Level.INFO, "info hello world");
        logger.log(Level.FINE, "fine hello world");
        logger.log(Level.FINER, "finer hello world");
        logger.log(Level.FINEST, "finest hello world");
        logger.log(Level.WARNING, "warning hello world");
        logger.log(Level.SEVERE, "severe hello world");
        logger.log(Level.ALL, "all hello world");
        return properties.getAppName() + ": " + properties.getDefaultGreeting();
    }

    @GetMapping
    public Iterable<User> getAll(){
        return service.getAll();
    }

    @Operation(
            summary = "Gets the user with given ID.",
            description = "Gets the user with given ID. If the user with given ID is not found this return 422.",
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "User ID")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "user object pulled from database",
                            content = @Content(schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Returned when user was not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public User get(@PathVariable String id){
        return service.findById(id);
    }

    @Operation(
            summary = "Creates new user.",
            description = "Creates new user from given request body and validates oll the inputs.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "user object created in database",
                            content = @Content(schema = @Schema(implementation = User.class))
                    )
            }
    )
    @PostMapping
    public User create(@Validated @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody User user){
        return service.saveUser(user);
    }

    @PutMapping
    public User update(@Validated @RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.deleteUser(id);
    }

    @PostMapping("/{id}/wishlist")
    public User addToWishlist(@PathVariable String id, @RequestBody AddToWishlistRequest request) {
        return service.addToWishlist(id, request.getItemIds());
    }

    @PostMapping("/{id}/post")
    public UserPostDto createPost(@PathVariable String id, @RequestBody UserPostRequest userPostRequest){
        User user = service.findById(id);
        return postService.create(user, userPostRequest.getTitle(), userPostRequest.getBody());
    }

    @GetMapping("/{id}/post")
    public List<UserPostDto> getAll(@PathVariable String id) {
        User user = service.findById(id);
        return postService.getUserPosts(user);
    }

}
