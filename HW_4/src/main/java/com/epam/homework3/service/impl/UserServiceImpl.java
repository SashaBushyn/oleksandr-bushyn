package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.mappers.UserMapper;
import com.epam.homework3.model.entity.User;
import com.epam.homework3.model.enums.Role;
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
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.getAllUsers()
                .stream()
                .map(UserMapper.INSTANCE::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        User user = UserMapper.INSTANCE.UserDtoToUser(userDto);
        user.setRole(Role.GUEST);
        user.setId(getNewId());
        user = userRepository.createUser(user);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("updateUser with email {}", email);
        User user = UserMapper.INSTANCE.UserDtoToUser(userDto);
        user = userRepository.updateUser(email, user);
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser with id {}", id);
        userRepository.deleteUser(id);
    }

    private Long getNewId() {
        Long id = userRepository.getAllUsers()
                .stream()
                .map(User::getId).max(java.util.Comparator.naturalOrder()).orElse(0L);
        id++;
        return id;
    }
}
