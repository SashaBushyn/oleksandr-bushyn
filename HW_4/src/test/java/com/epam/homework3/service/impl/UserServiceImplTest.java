package com.epam.homework3.service.impl;

import com.epam.homework3.UserUtil;
import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.mappers.UserMapper;
import com.epam.homework3.model.entity.User;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserTest() {
        User user = UserUtil.testUser(1);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser(user.getEmail());

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("id", equalTo(user.getId())),
                hasProperty("login", equalTo(user.getLogin()))));
    }

    @Test
    void getUserNotFoundTest() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityException.class, () -> userService.getUser(anyString()));
    }

    @Test
    void getListUsersTest() {
        Page<User> page = new PageImpl<>(Arrays.asList(mock(User.class)
                , mock(User.class)));
        Pageable pageable = mock(Pageable.class);
        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<UserDto> pageUserDto = userService.listUsers(pageable);

        assertEquals(pageUserDto.getTotalElements(), page.getTotalElements());
    }

    @Test
    void createUserTest() {
        User user = UserUtil.testUser(1);
        UserDto userDto = userMapper.userToUserDto(user);
        when(userRepository.save(user)).thenReturn(user);

        userDto = userService.createUser(userDto);

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("firstName", equalTo(user.getFirstName())),
                hasProperty("role", equalTo(user.getRole())),
                hasProperty("id", equalTo(user.getId())),
                hasProperty("login", equalTo(user.getLogin()))));
    }

    @Test
    void createUserUserAlreadyExistsTest() {
        UserDto userDto = UserDto.builder().email("test1@test.ua").build();

        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(EntityException.class, () -> userService.createUser(userDto));
    }

    @Test
    void updateUserFoundTest() {
        UserDto userDto = UserUtil.testUserDto(2);
        User persistedUser = UserUtil.testUser(1);
        User updatedUser = UserUtil.testUser(2);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(persistedUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        userDto = userService.updateUser(anyString(), userDto);
        assertThat(userDto, allOf(
                hasProperty("email", equalTo(updatedUser.getEmail())),
                hasProperty("firstName", equalTo(updatedUser.getFirstName())),
                hasProperty("role", equalTo(updatedUser.getRole())),
                hasProperty("id", equalTo(updatedUser.getId())),
                hasProperty("login", equalTo(updatedUser.getLogin()))));
    }

    @Test
    void updateUserNotFoundTest() {
        UserDto userDto = mock(UserDto.class);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(EntityException.class, () -> userService.updateUser(anyString(), userDto));
    }

    @Test
    void deleteUserTest() {
        when(userRepository.existsById(any())).thenReturn(true);

        userService.deleteUser(any());

        verify(userRepository, times(1)).deleteById(any());
    }

    @Test
    void deleteUserNotFoundTest() {
        when(userRepository.existsById(any())).thenReturn(false);
        assertThrows(EntityException.class, () -> userService.deleteUser(any()));
    }
}