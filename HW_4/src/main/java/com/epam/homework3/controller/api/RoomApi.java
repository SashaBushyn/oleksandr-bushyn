package com.epam.homework3.controller.api;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.controller.dto.group.OnCreate;
import com.epam.homework3.controller.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "room management API")
@RequestMapping("/api/v1/")
public interface RoomApi {
    @ApiOperation("get all rooms")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/room")
    List<RoomDto> getAllRooms();

    @ApiOperation("get room by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/room/{id}")
    RoomDto getRoom(@PathVariable Long id);

    @ApiOperation("create room")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/room")
    RoomDto createRoom(@RequestBody @Validated(OnCreate.class) RoomDto roomDto);

    @ApiOperation("update room")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/room/{id}")
    RoomDto updateRoom(@PathVariable Long id, @RequestBody @Validated(OnUpdate.class) RoomDto roomDto);

    @ApiOperation("delete room")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/room/{id}")
    void deleteRoom(@PathVariable Long id);
}
