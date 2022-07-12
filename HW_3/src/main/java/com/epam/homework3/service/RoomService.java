package com.epam.homework3.service;

import com.epam.homework3.controller.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto CreateRoom(RoomDto room);

    RoomDto GetRoom(Long id);

    List<RoomDto> GetAllRooms();

    RoomDto updateRoom(Long id, RoomDto room);

    void deleteRoom(Long id);
}
