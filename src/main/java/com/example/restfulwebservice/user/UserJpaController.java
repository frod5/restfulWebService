package com.example.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userJpaRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveEntity = userJpaRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(saveEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable Long id) {

        User user = userJpaRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("ID[%s] not found", id)));

        return user.getPosts();
    }
}
