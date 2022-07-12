package com.epam.homework3.repository.impl;

import com.epam.homework3.model.entity.User;
import com.epam.homework3.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> list = new ArrayList<>();

    @Override
    public User createUser(User user) {
        list.add(user);
        return user;
    }

    @Override
    public User getUser(String email) {
        return list.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow(() -> new RuntimeException("User is not found!"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(list);
    }

    @Override
    public User updateUser(String email, User user) {
        boolean isDeleted = list.removeIf(u -> u.getEmail().equals(email));
        if (isDeleted) {
            list.add(user);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        list.removeIf(user -> user.getId() == id);
    }
}
