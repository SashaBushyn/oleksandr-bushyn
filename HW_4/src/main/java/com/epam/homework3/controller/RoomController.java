package com.epam.homework3.controller;

import com.epam.homework3.controller.api.RoomApi;
import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoomController implements RoomApi {
    private final RoomService roomService;

    @Override
    public Page<RoomDto> getAllRooms(Pageable pageable) {
        log.info("request to get all rooms");
        return roomService.GetAllRooms(pageable);
    }

    @Override
    public RoomDto getRoom(Long id) {
        log.info("request to get room by id {}", id);
        return roomService.GetRoom(id);
    }

    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        log.info("request to create room");
        return roomService.CreateRoom(roomDto);
    }

    @Override
    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        log.info("request to update room by id {}");
        return roomService.updateRoom(id, roomDto);
    }

    @Override
    public void deleteRoom(Long id) {
        log.info("request to delete room by id");
        roomService.deleteRoom(id);
    }

    @Override
    public Page<RoomDto> getFreeRooms(LocalDate dateIn, LocalDate dateOut,
                                      Pageable pageable) {
        log.info(" request to get free rooms on dates ");
        return roomService.getFreeRoomsOnDates(dateIn, dateOut,pageable);
    }

    @Override
    public List<RoomDto> getFreeRoomsForOrder(Long id) {
        log.info(" request to get free rooms for order id {}", id);
        return  roomService.getFreeRoomsForOrder(id);
    }
}
