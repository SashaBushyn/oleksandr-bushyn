package com.epam.homework3.repository.impl;

import com.epam.homework3.model.entity.Booking;
import com.epam.homework3.repository.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingRepositoryImpl implements BookingRepository {
    private final List<Booking> bookingList = new ArrayList<>();

    @Override
    public Booking createBooking(Booking booking) {
        bookingList.add(booking);
        return booking;
    }

    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingList);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        boolean isDeleted = bookingList.removeIf(u -> u.getId() == id);
        if (isDeleted) {
            bookingList.add(booking);
        } else {
            throw new RuntimeException("User is not found!");
        }
        return booking;
    }

    @Override
    public void deleteBooking(Long id) {
        bookingList.removeIf(booking -> booking.getId() == id);
    }
}
