package com.epam.homework3.mappers;

import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.model.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    User updateUser(@MappingTarget User user, UserDto userDto);
}
