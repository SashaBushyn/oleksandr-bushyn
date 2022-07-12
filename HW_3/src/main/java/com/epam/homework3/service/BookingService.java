package com.epam.homework3.service;

import com.epam.homework3.controller.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto createBooking(BookingDto bookingDto);

    List<BookingDto> getAllBookings();

    List<BookingDto> getUserBookings(Long userId);

    BookingDto updateBooking(Long id, BookingDto bookingDto);

    void deleteBooking(Long id);
}
