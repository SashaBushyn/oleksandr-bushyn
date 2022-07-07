package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.model.entity.Booking;
import com.epam.homework3.repository.BookingRepository;
import com.epam.homework3.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        log.info("create booking for user id{}", bookingDto.getUserId());
        Booking booking = mapBookingDtoToBooking(bookingDto);
        booking = bookingRepository.createBooking(booking);
        return mapBookingToBookingDto(booking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        log.info("get all bookings");
        return bookingRepository.getAllBookings().stream().map(this::mapBookingToBookingDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getUserBookings(Long userId) {
        log.info("get userid {}  bookings", userId);
        return bookingRepository.getAllBookings().stream().filter(booking -> booking.getUserId() == userId)
                .map(this::mapBookingToBookingDto).collect(Collectors.toList());
    }

    @Override
    public BookingDto updateBooking(Long id, BookingDto bookingDto) {
        return null;
    }

    @Override
    public void deleteBooking(Long id) {
        log.info("delete booking with id {}", id);
        bookingRepository.deleteBooking(id);
    }

    private BookingDto mapBookingToBookingDto(Booking booking) {
        return BookingDto.builder().roomId(booking.getRoomId()).dateCreation(booking.getDateCreation())
                .dateIn(booking.getDateIn()).dateOut(booking.getDateOut()).id(booking.getId())
                .userId(booking.getUserId()).build();
    }

    private Booking mapBookingDtoToBooking(BookingDto bookingDto) {
        return Booking.builder().roomId(bookingDto.getRoomId()).dateCreation(bookingDto.getDateCreation())
                .dateIn(bookingDto.getDateIn()).dateOut(bookingDto.getDateOut()).id(bookingDto.getId())
                .userId(bookingDto.getUserId()).build();
    }
}
