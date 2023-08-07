package com.example.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Long id) {
        User user = userJpaRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", id)));

        //HATEOAS
        return EntityModel.of(user, linkTo(methodOn(UserJpaController.class).retrieveAllUsers()).withRel("all-users"));
    }
}
