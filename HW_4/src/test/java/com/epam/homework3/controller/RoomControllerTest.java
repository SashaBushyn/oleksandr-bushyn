package com.epam.homework3.controller;

import com.epam.homework3.RoomUtil;
import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RoomController.class)
class RoomControllerTest {
    @MockBean
    private RoomService roomService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllRoomsTest() throws Exception {
        Page<RoomDto> roomDtoPage = new PageImpl<>(Arrays.asList(RoomUtil.roomDtoTest(1), RoomUtil.roomDtoTest(2)));
        when(roomService.GetAllRooms(any(Pageable.class))).thenReturn(roomDtoPage);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/room"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String liststr = new ObjectMapper().writeValueAsString(roomDtoPage);
        assertEquals(liststr, actualJsonResponse);
    }

    @Test
    void getRoomTest() throws Exception {
        RoomDto roomDto = RoomUtil.roomDtoTest(1);
        when(roomService.GetRoom(anyLong())).thenReturn(roomDto);
        mockMvc.perform(get("/api/v1/room/" + roomDto.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(roomDto.getId()));
    }

    @Test
    void createRoomTest() throws Exception {
        RoomDto roomDto = RoomUtil.roomDtoTest(1);
        roomDto.setId(null);
        String roomStr = new ObjectMapper().writeValueAsString(roomDto);

        when(roomService.CreateRoom(roomDto)).thenReturn(roomDto);

        mockMvc.perform(post("/api/v1/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(roomStr))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(result.getResponse().getContentAsString(), roomStr));
    }

    @Test
    void updateRoomTest() throws Exception {
        RoomDto roomDto = RoomUtil.roomDtoTest(1);
        String roomStr = new ObjectMapper().writeValueAsString(roomDto);
        when(roomService.updateRoom(roomDto.getId(),roomDto)).thenReturn(roomDto);

        mockMvc.perform(put("/api/v1/room/"+ roomDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(roomStr))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(result.getResponse().getContentAsString(), roomStr));
    }

    @Test
    void deleteRoomTest() throws Exception {
        doNothing().when(roomService).deleteRoom(anyLong());

        mockMvc.perform(delete("/api/v1/room/" + anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void getFreeRoomsTest() throws Exception {
        Page<RoomDto> roomDtoPage = new PageImpl<>(Arrays.asList(RoomUtil.roomDtoTest(1), RoomUtil.roomDtoTest(2)));
        when(roomService.getFreeRoomsOnDates(any(LocalDate.class), any(LocalDate.class), any(Pageable.class)))
                .thenReturn(roomDtoPage);
        String liststr = new ObjectMapper().writeValueAsString(roomDtoPage);

        mockMvc.perform(get("/api/v1/room/FreeRooms/")
                        .param("dateIn", LocalDate.now().toString())
                        .param("dateOut", LocalDate.now().plusDays(1).toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(liststr, result.getResponse().getContentAsString()));
    }

    @Test
    void getFreeRoomsForOrderTest() throws Exception {
        List<RoomDto> roomDtoList = Arrays.asList(RoomUtil.roomDtoTest(1), RoomUtil.roomDtoTest(2));
        when(roomService.getFreeRoomsForOrder(anyLong())).thenReturn(roomDtoList);
        String liststr = new ObjectMapper().writeValueAsString(roomDtoList);

        mockMvc.perform(get("/api/v1/room/FreeRooms/" + anyLong()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(liststr, result.getResponse().getContentAsString()));
    }
}