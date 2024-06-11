package com.example.demo.users;

import com.example.demo.items.ItemsRepository;
import com.example.demo.items.model.Item;
import com.example.demo.users.model.User;
import com.example.demo.util.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ItemsRepository itemsRepository;

    @Autowired
    public UserService(UserRepository repository, ItemsRepository itemsRepository){
        this.userRepository = repository;
        this.itemsRepository = itemsRepository;
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

    public User addToWishlist(String userId, List<String> itemIds) {
        User user = findById(userId);
        List<Item> items = itemsRepository.findByIdIn(itemIds);

        user.setWishlist(items);
        return userRepository.save(user);
    }

}
