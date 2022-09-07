package com.epam.homework3.controller;

import com.epam.homework3.controller.api.BookingApi;
import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookingController implements BookingApi {
    private final BookingService service;

    @Override
    public Page<BookingDto> getAllBookings(Pageable pageable) {
        log.info("request to get all bookings");
        return service.getAllBookings(pageable);
    }

    @Override
    public Page<BookingDto> getUserBookings(Long id, Pageable pageable) {
        log.info(" request to get user`s id={} bookings ", id);
        return service.getUserBookings(id, pageable);
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        log.info("request to create booking ");
        return service.createBooking(bookingDto);
    }

    @Override
    public void deleteBooking(Long id) {
        log.info("request to delete booking id {}", id);
        service.deleteBooking(id);
    }
}
