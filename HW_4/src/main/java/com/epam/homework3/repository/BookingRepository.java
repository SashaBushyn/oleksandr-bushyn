package com.epam.homework3.repository;

import com.epam.homework3.model.entity.Booking;

import java.util.List;

public interface BookingRepository {

    Booking createBooking(Booking booking);

    List<Booking> getAllBookings();

    Booking updateBooking(Long id, Booking booking);

    void deleteBooking(Long id);
}
