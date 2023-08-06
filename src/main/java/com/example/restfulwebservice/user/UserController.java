package com.example.restfulwebservice.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        User user = userRepository.findOne(id);

        if(ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(String.format("ID[%s]",id));
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Long savedId = userRepository.save(user);

        URI loacation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(loacation).build();
    }
}
