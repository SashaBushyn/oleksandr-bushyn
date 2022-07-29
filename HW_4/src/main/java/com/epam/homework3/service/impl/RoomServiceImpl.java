package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.mappers.RoomMapper;
import com.epam.homework3.model.entity.Room;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.RoomRepository;
import com.epam.homework3.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomDto CreateRoom(RoomDto roomDto) {
        log.info("createRoom with number {}", roomDto.getRoomNumber());
        Room room = roomMapper.roomDtoToRoom(roomDto);
        room = roomRepository.save(room);
        return roomMapper.roomToRoomDto(room);
    }

    @Override
    public RoomDto GetRoom(Long id) {
        log.info("get room by id {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityException("room with id: " + id + " is not found"));
        return roomMapper.roomToRoomDto(room);
    }

    @Override
    public Page<RoomDto> GetAllRooms(Pageable pageable) {
        log.info("get all rooms");
        return new PageImpl<>(roomRepository.findAll(pageable)
                .stream().map(roomMapper::roomToRoomDto)
                .collect(Collectors.toList()));
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        log.info("update room id = {}", id);
        Room persistedRoom = roomRepository.getById(id);
        persistedRoom = roomMapper.updateRoom(persistedRoom, roomDto);
        roomRepository.save(persistedRoom);
        return roomMapper.roomToRoomDto(persistedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        log.info("delete room with id {}", id);
        roomRepository.deleteById(id);
    }

    @Override
    public Page<RoomDto> getFreeRoomsOnDates(LocalDate dateIn, LocalDate dateOut, Pageable pageable) {
        log.info("get free rooms on dates {}, {}", dateIn, dateOut);
        return roomRepository.getFreeRoomsOnDates(dateIn, dateOut, pageable);
    }

    @Override
    public List<RoomDto> getFreeRoomsForOrder(Long id) {
        return roomRepository.getFreeRoomsForOrder(id);
    }
}
