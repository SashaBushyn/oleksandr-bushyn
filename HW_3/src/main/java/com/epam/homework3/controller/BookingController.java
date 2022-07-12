package com.epam.homework3.controller;

import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<BookingDto> getAllBookings() {
        log.info("get all bookings");
        return service.getAllBookings();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/user")
    public List<BookingDto> getUserBookings(@PathVariable Long id) {
        log.info("get user`s id={} bookings ", id);
        return service.getUserBookings(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public BookingDto createBooking(@RequestBody BookingDto bookingDto) {
        log.info("create booking ");
        return service.createBooking(bookingDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public BookingDto updateRoom(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        log.info("update booking id {}", id);
        return service.updateBooking(id, bookingDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteRoom(@PathVariable Long id) {
        log.info("delete booking id {}", id);
        service.deleteBooking(id);
    }
}
