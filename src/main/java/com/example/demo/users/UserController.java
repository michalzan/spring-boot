package com.example.demo.users;

import com.example.demo.users.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UserController {

    private final HashMap<String, User> users = new HashMap<>();

    @GetMapping("hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping
    public Collection<User> getAll(){
        return users.values();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable String id){
        User user = users.get(id);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!!");
        return user;
    }

    @PostMapping
    public User create(@RequestBody User user){
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable String id){
        User existing = users.get(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!!");
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPhone(user.getPhone());
        return existing;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        User existing = users.get(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!!");
        users.remove(id);
    }

}
