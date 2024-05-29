package com.example.demo.users;

import com.example.demo.users.model.User;
import com.example.demo.util.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository){
        userRepository = repository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user){
        // normally we would use repository.existsById(), but we wanted to demonstrate .orElseThrow
        userRepository.findById(user.getId()).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        return userRepository.save(user);
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }

    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
        userRepository.delete(user);
    }

}
