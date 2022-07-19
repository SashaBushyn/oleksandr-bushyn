package com.epam.homework3.controller;

import com.epam.homework3.controller.api.UserApi;
import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public UserDto getUser(String email) {
        log.info("request to get user by email {}", email);
        return userService.getUser(email);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("request to get all users");
        return userService.listUsers();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("request to create user");
        return userService.createUser(userDto);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("request to update user by email {}", email);
        return userService.updateUser(email, userDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        log.info("request to delete user by id {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
