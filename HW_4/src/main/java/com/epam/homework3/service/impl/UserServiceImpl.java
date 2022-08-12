package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.mappers.UserMapper;
import com.epam.homework3.model.entity.User;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.UserRepository;
import com.epam.homework3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUser(String email) {
        log.info("getUser by email {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityException("user with email: " + email + " is not found"));
        return userMapper.userToUserDto(user);
    }

    @Override
    public Page<UserDto> listUsers(Pageable pageable) {
        log.info("get all users");
        return new PageImpl<>(userRepository.findAll(pageable)
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList()));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        if (!userRepository.existsByEmail(userDto.getEmail())) {
            User user = userMapper.userDtoToUser(userDto);
            user = userRepository.save(user);
            return userMapper.userToUserDto(user);
        }
        throw new EntityException("user with email " + userDto.getEmail() + " already exists");
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("updateUser with email {}", email);
        User persistedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityException("user with email: " + email + " is not found"));
        persistedUser = userMapper.updateUser(persistedUser, userDto);
        persistedUser = userRepository.save(persistedUser);
        return userMapper.userToUserDto(persistedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser with id {}", id);
        if (!userRepository.existsById(id)) throw new EntityException("user with id " + id + " is not found");
        userRepository.deleteById(id);
    }
}
