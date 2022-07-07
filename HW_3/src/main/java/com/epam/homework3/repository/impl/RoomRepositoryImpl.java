package com.epam.homework3.repository.impl;

import com.epam.homework3.model.entity.Room;
import com.epam.homework3.repository.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomRepositoryImpl implements RoomRepository {

    private final List<Room> list = new ArrayList<>();

    @Override
    public Room CreateRoom(Room room) {
        list.add(room);
        return room;
    }

    @Override
    public Room GetRoom(Long id) {
        return list.stream().filter(room -> room.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("Room is not found"));
    }

    @Override
    public List<Room> GetAllRooms() {
        return new ArrayList<>(list);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        boolean isDeleted = list.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            list.add(room);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return room;
    }

    @Override
    public void deleteRoom(Long id) {
        list.removeIf(room -> room.getId() == id);
    }
}
