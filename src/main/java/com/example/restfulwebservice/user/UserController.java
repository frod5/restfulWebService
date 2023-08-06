package com.example.restfulwebservice.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @PostMapping("/users")
    public Long createUser(@RequestBody User user) {
        user.setJoinDate(LocalDateTime.now());
        return userRepository.save(user);
    }
}
