package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.mappers.BookingMapper;
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
        Booking booking = BookingMapper.INSTANCE.bookingDtoToBooking(bookingDto);
        booking.setId(getNewId());
        booking = bookingRepository.createBooking(booking);
        return BookingMapper.INSTANCE.bookingToBookingDto(booking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        log.info("get all bookings");
        return bookingRepository.getAllBookings()
                .stream()
                .map(BookingMapper.INSTANCE::bookingToBookingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getUserBookings(Long userId) {
        log.info("get userid {}  bookings", userId);
        return bookingRepository.getAllBookings()
                .stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .map(BookingMapper.INSTANCE::bookingToBookingDto)
                .collect(Collectors.toList());
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

    private Long getNewId() {
        Long id = bookingRepository.getAllBookings()
                .stream()
                .map(Booking::getId).max(java.util.Comparator.naturalOrder())
                .orElse(0L);
        id++;
        return id;
    }
}
