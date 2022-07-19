package com.epam.homework3.controller.api;

import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.controller.dto.group.OnCreate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "user management API")
@RequestMapping("/api/v1/")
public interface UserApi {
    @ApiOperation("get user by email")
    @GetMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    UserDto getUser(@PathVariable String email);

    @ApiOperation("get all users")
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    List<UserDto> getAllUsers();

    @ApiOperation("create user")
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    UserDto createUser(@Validated(OnCreate.class) @RequestBody UserDto userDto);

    @ApiOperation("update user")
    @PutMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    UserDto updateUser(@PathVariable String email, @RequestBody UserDto userDto);

    @ApiOperation("delete user")
    @DeleteMapping("/user/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
