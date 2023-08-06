package com.example.restfulwebservice.user;

import java.util.List;

public interface UserRepository {
    Long save(User user);

    List<User> findAll();

    User findOne(Long id);

    User deleteById(Long id);
}
