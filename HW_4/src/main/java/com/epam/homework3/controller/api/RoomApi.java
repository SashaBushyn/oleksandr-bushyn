package com.epam.homework3.controller.api;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.controller.dto.group.OnCreate;
import com.epam.homework3.controller.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Api(tags = "room management API")
@RequestMapping("/api/v1/")
public interface RoomApi {
    @ApiOperation("get all rooms")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/room")
    Page<RoomDto> getAllRooms(Pageable pageable);

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
    @PutMapping("/room/{id}")
    RoomDto updateRoom(@PathVariable Long id, @RequestBody @Validated(OnUpdate.class) RoomDto roomDto);

    @ApiOperation("delete room")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/room/{id}")
    void deleteRoom(@PathVariable Long id);

    @ApiOperation("Get free rooms  on date")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/room/FreeRooms")
    Page<RoomDto> getFreeRooms(@RequestParam("dateIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateIn,
                               @RequestParam("dateOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOut,
                               Pageable pageable);

    @ApiOperation("Get free rooms for order")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/room/FreeRooms/{id}")
    List<RoomDto> getFreeRoomsForOrder(@PathVariable Long id);


}
