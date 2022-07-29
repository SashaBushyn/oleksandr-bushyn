package com.epam.homework3.service;

import com.epam.homework3.controller.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    RoomDto CreateRoom(RoomDto room);

    RoomDto GetRoom(Long id);

    Page<RoomDto> GetAllRooms(Pageable pageable);

    RoomDto updateRoom(Long id, RoomDto room);

    void deleteRoom(Long id);

    Page<RoomDto> getFreeRoomsOnDates(LocalDate dateIn, LocalDate dateOut, Pageable pageable);

    List<RoomDto> getFreeRoomsForOrder(Long id);
}
