package com.epam.homework3.service;

import com.epam.homework3.controller.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUser(String email);

    List<UserDto> listUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(Long id);
}
