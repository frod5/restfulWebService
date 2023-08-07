package com.example.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpa")
@Slf4j
public class UserJpaController {

    private final UserJpaRepository userJpaRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userJpaRepository.findAll();
    }
}
