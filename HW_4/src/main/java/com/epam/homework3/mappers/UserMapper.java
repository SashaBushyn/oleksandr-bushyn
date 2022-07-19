package com.epam.homework3.mappers;

import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    User UserDtoToUser(UserDto userDto);
}
