package com.epam.homework3.repository;

import com.epam.homework3.model.entity.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user);

    User getUser(String email);

    List<User> getAllUsers();

    User updateUser(String email, User user);

    void deleteUser(Long id);

}
