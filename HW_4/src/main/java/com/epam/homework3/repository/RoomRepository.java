package com.epam.homework3.repository;

import com.epam.homework3.model.entity.Room;

import java.util.List;

public interface RoomRepository {

    Room createRoom(Room room);

    Room getRoom(Long id);

    List<Room> getAllRooms();

    Room updateRoom(Long id, Room room);

    void deleteRoom(Long id);
}
