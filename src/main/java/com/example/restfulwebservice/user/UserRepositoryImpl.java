package com.example.restfulwebservice.user;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L,"Kenneth", LocalDateTime.now()));
        users.add(new User(2L,"Alice", LocalDateTime.now()));
        users.add(new User(3L,"Elena", LocalDateTime.now()));
    }

    private static long usersCount = 3;
    @Override
    public Long save(User user) {

        if(ObjectUtils.isEmpty( user.getId())) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user.getId();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findOne(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
