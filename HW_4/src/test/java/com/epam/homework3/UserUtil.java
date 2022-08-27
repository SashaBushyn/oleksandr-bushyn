package com.epam.homework3;

import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.model.entity.User;
import com.epam.homework3.model.enums.Role;

public class UserUtil {
    public static User testUser(int i) {
        return User.builder()
                .blocked(false)
                .role(Role.ADMIN)
                .password("password" + i)
                .firstName("TestFirstName" + i)
                .lastName("testLastName" + i)
                .id(1L + i)
                .email("test" + i + "@test.ua")
                .login("test" + i).build();
    }

    public static UserDto testUserDto(int i) {
        return UserDto.builder()
                .blocked(false)
                .role(Role.ADMIN)
                .password("password" + i)
                .firstName("TestFirstName" + i)
                .lastName("testLastName" + i)
                .id(1L + i)
                .email("test" + i + "@test.ua")
                .login("test" + i).build();
    }

}
