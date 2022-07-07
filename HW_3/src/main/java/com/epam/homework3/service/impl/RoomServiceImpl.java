package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.model.entity.Room;
import com.epam.homework3.repository.RoomRepository;
import com.epam.homework3.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public RoomDto CreateRoom(RoomDto roomDto) {
        log.info("createRoom with number {}", roomDto.getRoomNumber());
        Room room = mapRoomrDtoToRoom(roomDto);
        room = roomRepository.CreateRoom(room);
        return mapRoomToRoomDto(room);
    }

    @Override
    public RoomDto GetRoom(Long id) {
        log.info("get room by id {}", id);
        Room room = roomRepository.GetRoom(id);
        return mapRoomToRoomDto(room);
    }

    @Override
    public List<RoomDto> GetAllRooms() {
        log.info("get all rooms");
        return roomRepository.GetAllRooms().stream().map(this::mapRoomToRoomDto).collect(Collectors.toList());
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        log.info("update room id = {}", id);
        Room room = mapRoomrDtoToRoom(roomDto);
        roomRepository.updateRoom(id, room);
        return mapRoomToRoomDto(room);
    }

    @Override
    public void deleteRoom(Long id) {
        log.info("delete room with id {}", id);
        roomRepository.deleteRoom(id);
    }

    private RoomDto mapRoomToRoomDto(Room room) {
        return RoomDto.builder().roomClass(room.getRoomClass()).personsNumber(room.getPersonsNumber())
                .blocked(room.isBlocked()).RoomNumber(room.getRoomNumber()).id(room.getId()).build();
    }

    private Room mapRoomrDtoToRoom(RoomDto roomDto) {
        return Room.builder().roomClass(roomDto.getRoomClass()).personsNumber(roomDto.getPersonsNumber())
                .blocked(roomDto.isBlocked()).RoomNumber(roomDto.getRoomNumber()).id(roomDto.getId()).build();
    }
}
