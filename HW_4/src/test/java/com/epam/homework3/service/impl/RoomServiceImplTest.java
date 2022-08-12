package com.epam.homework3.service.impl;

import com.epam.homework3.RoomUtil;
import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.mappers.RoomMapper;
import com.epam.homework3.model.entity.Room;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class})
class RoomServiceImplTest {
    @Mock
    private RoomRepository roomRepository;
    @Spy
    RoomMapper roomMapper = Mappers.getMapper(RoomMapper.class);
    @InjectMocks
    RoomServiceImpl roomService;

    @Test
    void createRoomTest() {
        Room room = RoomUtil.roomTest(1);
        RoomDto roomDto = RoomUtil.roomDtoTest(1);
        when(roomRepository.save(room)).thenReturn(room);

        roomDto = roomService.CreateRoom(roomDto);

        assertThat(roomDto, allOf(
                hasProperty("id", equalTo(room.getId())),
                hasProperty("roomNumber", equalTo(room.getRoomNumber())),
                hasProperty("roomClass", equalTo(room.getRoomClass()))));
    }

    @Test
    void getRoomTest() {
        Room room = RoomUtil.roomTest(1);
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(room));
        RoomDto roomDto = roomService.GetRoom(anyLong());

        assertThat(roomDto, allOf(
                hasProperty("id", equalTo(room.getId())),
                hasProperty("roomNumber", equalTo(room.getRoomNumber())),
                hasProperty("roomClass", equalTo(room.getRoomClass()))));
    }

    @Test
    void getRoomNotFoundTest() {
        when(roomRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityException.class, () -> roomService.GetRoom(anyLong()));
    }

    @Test
    void getAllRoomsTest() {
        Page<Room> page = new PageImpl<>(Arrays.asList(mock(Room.class), mock(Room.class)));
        Pageable pageable = mock(Pageable.class);
        when(roomRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<RoomDto> pageUserDto = roomService.GetAllRooms(pageable);

        assertEquals(pageUserDto.getTotalElements(), page.getTotalElements());
    }

    @Test
    void updateRoomTest() {
        RoomDto roomDto = RoomUtil.roomDtoTest(2);
        Room persistedRoom = RoomUtil.roomTest(1);
        Room updatedRoom = RoomUtil.roomTest(2);
        when(roomRepository.getById(anyLong())).thenReturn(persistedRoom);
        when(roomRepository.save(updatedRoom)).thenReturn(persistedRoom);

        roomDto = roomService.updateRoom(anyLong(), roomDto);
        assertThat(roomDto, allOf(
                hasProperty("roomClass", equalTo(updatedRoom.getRoomClass())),
                hasProperty("roomNumber", equalTo(updatedRoom.getRoomNumber())),
                hasProperty("price", equalTo(updatedRoom.getPrice())),
                hasProperty("id", equalTo(updatedRoom.getId())),
                hasProperty("personsNumber", equalTo(updatedRoom.getPersonsNumber()))));
    }

    @Test
    void getFreeRoomsOnDatesTest() {
        Page<Room> page = new PageImpl<>(Arrays.asList(mock(Room.class), mock(Room.class)));
        Pageable pageable = mock(Pageable.class);
        LocalDate dateIn = LocalDate.now();
        LocalDate dateOut = LocalDate.now().plusDays(1);
        when(roomRepository.getFreeRoomsOnDates(dateIn, dateOut, pageable)).thenReturn(page);

        Page<RoomDto> roomDtoPage = roomService.getFreeRoomsOnDates(dateIn, dateOut, pageable);
        assertEquals(roomDtoPage.getTotalElements(), page.getTotalElements());
    }

    @Test
    void getFreeRoomsOnDatesNoRoomsTest() {
        Page<Room> page = new PageImpl<>(new ArrayList<>());
        Pageable pageable = mock(Pageable.class);
        LocalDate dateIn = LocalDate.now();
        LocalDate dateOut = LocalDate.now().plusDays(1);
        when(roomRepository.getFreeRoomsOnDates(dateIn, dateOut, pageable)).thenReturn(page);

        Page<RoomDto> roomDtoPage = roomService.getFreeRoomsOnDates(dateIn, dateOut, pageable);
        assertEquals(roomDtoPage.getTotalElements(), page.getTotalElements());
    }

    @Test
    void getFreeRoomsForOrderTest() {
        List<Room> rooms = Arrays.asList(mock(Room.class), mock(Room.class));
        Pageable pageable = mock(Pageable.class);
        LocalDate dateIn = LocalDate.now();
        LocalDate dateOut = LocalDate.now().plusDays(1);
        when(roomRepository.getFreeRoomsForOrder(anyLong())).thenReturn(rooms);

        List<RoomDto> dtoList = roomService.getFreeRoomsForOrder(anyLong());
        assertEquals(dtoList.size(), rooms.size());
    }

    @Test
    void deleteRoomTest() {
        when(roomRepository.existsById(any())).thenReturn(true);

        roomService.deleteRoom(any());

        verify(roomRepository, times(1)).deleteById(any());
    }
}