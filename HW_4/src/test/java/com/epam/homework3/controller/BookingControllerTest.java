package com.epam.homework3.controller;

import com.epam.homework3.BookingUtil;
import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookingController.class)
class BookingControllerTest {
    @MockBean
    private BookingService bookingService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void getAllBookingsTest() throws Exception {
        Page<BookingDto> bookingDtoPage = new PageImpl<>(Arrays.asList(BookingUtil.testBookingDto(1), BookingUtil.testBookingDto(1)));
        when(bookingService.getAllBookings(any(Pageable.class))).thenReturn(bookingDtoPage);

        mockMvc.perform(get("/api/v1/booking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> Assertions.assertEquals(jsonFromObject(bookingDtoPage), result.getResponse().getContentAsString()));
    }

    @Test
    void getUserBookingsTest() throws Exception {
        Page<BookingDto> bookingDtoPage = new PageImpl<>(Arrays.asList(BookingUtil.testBookingDto(1), BookingUtil.testBookingDto(1)));
        when(bookingService.getUserBookings(1L, PageRequest.of(0,20))).thenReturn(bookingDtoPage);

        mockMvc.perform(get("/api/v1/booking/" + 1L + "/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> Assertions.assertEquals(jsonFromObject(bookingDtoPage), result.getResponse().getContentAsString()));
    }

    @Test
    void createBookingTest() throws Exception {
        BookingDto bookingDto = BookingUtil.testBookingDto(1);
        bookingDto.setId(null);
        when(bookingService.createBooking(bookingDto)).thenReturn(bookingDto);
        mockMvc.perform(post("/api/v1/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFromObject(bookingDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> Assertions.assertEquals(jsonFromObject(bookingDto), result.getResponse().getContentAsString()));

    }

    @Test
    void deleteBookingTest() throws Exception {
        doNothing().when(bookingService).deleteBooking(anyLong());

        mockMvc.perform(delete("/api/v1/booking/" + anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    private String jsonFromObject(Object o) {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writeValueAsString(o);
    }
}