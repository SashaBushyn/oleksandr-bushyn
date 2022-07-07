package com.epam.homework3.controller;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/room")
    public List<RoomDto> getAllRooms() {
        return roomService.GetAllRooms();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/room/{id}")
    public RoomDto getRoom(@PathVariable Long id) {
        return roomService.GetRoom(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/room")
    public RoomDto createRoom(@RequestBody RoomDto roomDto) {
        return roomService.CreateRoom(roomDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/room/{id}")
    public RoomDto updateRoom(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        return roomService.updateRoom(id, roomDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/room/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
