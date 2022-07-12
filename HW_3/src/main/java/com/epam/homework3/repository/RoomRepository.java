package com.epam.homework3.repository;

import com.epam.homework3.model.entity.Room;

import java.util.List;

public interface RoomRepository {

    Room CreateRoom(Room room);

    Room GetRoom(Long id);

    List<Room> GetAllRooms();

    Room updateRoom(Long id, Room room);

    void deleteRoom(Long id);
}
