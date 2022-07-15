package com.epam.homework3.repository.impl;

import com.epam.homework3.model.entity.User;
import com.epam.homework3.model.exception.EntityException;
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
        return list.stream().filter(user -> user.getEmail().equals(email)).findFirst()
                .orElseThrow(() -> new EntityException("user with email "+ email +" is  not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(list);
    }

    @Override
    public User updateUser(String email, User user) {
        User userRep = getUser(email);
        boolean isDeleted = list.removeIf(u -> u.getEmail().equals(email));
        if (isDeleted) {
            user.setId(userRep.getId());
            user.setEmail(userRep.getEmail());
            user.setRole(userRep.getRole());
            user.setBlocked(userRep.isBlocked());
            user.setPassword(user.getPassword());
            list.add(user);
        } else {
            throw new EntityException("user with email "+ email +" is  not found");
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        list.removeIf(user -> user.getId().equals(id));
    }
}
