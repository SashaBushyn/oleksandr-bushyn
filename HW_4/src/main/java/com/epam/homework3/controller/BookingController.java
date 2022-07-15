package com.epam.homework3.controller;

import com.epam.homework3.controller.api.BookingApi;
import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookingController implements BookingApi {
    private final BookingService service;

    @Override
    public List<BookingDto> getAllBookings() {
        log.info("request to get all bookings");
        return service.getAllBookings();
    }

    @Override
    public List<BookingDto> getUserBookings(Long id) {
        log.info(" request to get user`s id={} bookings ", id);
        return service.getUserBookings(id);
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        log.info("request to create booking ");
        return service.createBooking(bookingDto);
    }

    @Override
    public BookingDto updateBooking(Long id, BookingDto bookingDto) {
        log.info("request to update booking id {}", id);
        return service.updateBooking(id, bookingDto);
    }

    @Override
    public void deleteBooking(Long id) {
        log.info("request to delete booking id {}", id);
        service.deleteBooking(id);
    }
}
