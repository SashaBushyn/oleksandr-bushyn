package com.epam.homework3.service;

import com.epam.homework3.controller.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDto getUser(String email);

    Page<UserDto> listUsers(Pageable pageable);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(Long id);
}
