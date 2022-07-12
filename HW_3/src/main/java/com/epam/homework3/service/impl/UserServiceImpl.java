package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.model.entity.User;
import com.epam.homework3.repository.UserRepository;
import com.epam.homework3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String email) {
        log.info("getUser by email {}", email);
        User user = userRepository.getUser(email);
        return mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.getAllUsers().stream().map(this::mapUserToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        User user = mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("updateUser with email {}", email);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.updateUser(email, user);
        return mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser with id {}", id);
        userRepository.deleteUser(id);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder().firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).login(user.getLogin()).build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder().firstName(userDto.getFirstName()).lastName(userDto.getLastName()).email(userDto.getEmail()).build();
    }

}
