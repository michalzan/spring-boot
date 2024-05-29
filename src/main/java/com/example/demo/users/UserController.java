package com.example.demo.users;

import com.example.demo.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UserController {

    private final HashMap<String, User> users = new HashMap<>();
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping
    public Iterable<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable String id){
        return service.findById(id);
    }

    @PostMapping
    public User create(@Validated @RequestBody User user){
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

}
