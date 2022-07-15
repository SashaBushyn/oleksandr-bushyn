package com.epam.homework3.repository.impl;

import com.epam.homework3.model.entity.Room;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomRepositoryImpl implements RoomRepository {

    private final List<Room> list = new ArrayList<>();

    @Override
    public Room createRoom(Room room) {
        list.add(room);
        return room;
    }

    @Override
    public Room getRoom(Long id) {
        return list.stream().filter(room -> room.getId().equals(id))
                .findFirst().orElseThrow(() -> new EntityException("room with id "+ id +" is  not found"));
    }

    @Override
    public List<Room> getAllRooms() {
        return new ArrayList<>(list);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        boolean isDeleted = list.removeIf(u -> u.getId().equals(id));
        if (isDeleted) {
            list.add(room);
        } else {
            throw new EntityException("room with id "+ id +" is  not found");
        }
        return room;
    }

    @Override
    public void deleteRoom(Long id) {
        list.removeIf(room -> room.getId().equals(id));
    }
}
