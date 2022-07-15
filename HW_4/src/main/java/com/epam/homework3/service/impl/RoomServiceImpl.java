package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.mappers.RoomMapper;
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
        Room room = RoomMapper.INSTANCE.roomDtoToRoom(roomDto);
        room.setId(getNewId());
        room = roomRepository.createRoom(room);
        return RoomMapper.INSTANCE.roomToRoomDto(room);
    }

    @Override
    public RoomDto GetRoom(Long id) {
        log.info("get room by id {}", id);
        Room room = roomRepository.getRoom(id);
        return RoomMapper.INSTANCE.roomToRoomDto(room);
    }

    @Override
    public List<RoomDto> GetAllRooms() {
        log.info("get all rooms");
        return roomRepository.getAllRooms()
                .stream()
                .map(RoomMapper.INSTANCE::roomToRoomDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        log.info("update room id = {}", id);
        Room room = RoomMapper.INSTANCE.roomDtoToRoom(roomDto);
        roomRepository.updateRoom(id, room);
        return RoomMapper.INSTANCE.roomToRoomDto(room);
    }

    @Override
    public void deleteRoom(Long id) {
        log.info("delete room with id {}", id);
        roomRepository.deleteRoom(id);
    }

    private Long getNewId() {
        Long id = roomRepository.getAllRooms()
                .stream()
                .map(Room::getId).max(java.util.Comparator.naturalOrder()).orElse(0L);
        id++;
        return id;
    }
}
