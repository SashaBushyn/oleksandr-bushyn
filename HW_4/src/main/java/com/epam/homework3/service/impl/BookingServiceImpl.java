package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.mappers.BookingMapper;
import com.epam.homework3.model.entity.Booking;
import com.epam.homework3.model.entity.ReservedRooms;
import com.epam.homework3.model.entity.Room;
import com.epam.homework3.model.entity.User;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.BookingRepository;
import com.epam.homework3.repository.ReservedRoomsRepository;
import com.epam.homework3.repository.RoomRepository;
import com.epam.homework3.repository.UserRepository;
import com.epam.homework3.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservedRoomsRepository reservedRoomsRepository;

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        log.info("create booking for user id{}", bookingDto.getUserId());
        Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);
        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new EntityException("user with id: " + bookingDto.getUserId() + " is not found"));
        Room room = roomRepository.findById(bookingDto.getRoomId())
                .orElseThrow(() -> new EntityException("room with id: " + bookingDto.getRoomId() + " is not found"));
        booking.setRoom(room);
        booking.setUser(user);
        booking = bookingRepository.save(booking);
        int amountOfDays = Period.between(booking.getDateIn(), booking.getDateOut()).getDays();
        for (int i = 0; i <= amountOfDays; i++) {
            ReservedRooms reservedRooms = new ReservedRooms();
            reservedRooms.setRoom(room);
            reservedRooms.setBooking(booking);
            reservedRooms.setDateOfReserve(booking.getDateIn().plusDays(i));
            reservedRoomsRepository.save(reservedRooms);
        }
        return bookingMapper.bookingToBookingDto(booking);
    }

    @Override
    public Page<BookingDto> getAllBookings(Pageable pageable) {
        log.info("get all bookings");
        return new PageImpl<>(bookingRepository.findAll(pageable)
                .stream()
                .map(bookingMapper::bookingToBookingDto)
                .collect(Collectors.toList()));
    }

    @Override
    public Page<BookingDto> getUserBookings(Long userId, Pageable pageable) {
        log.info("get userid {}  bookings", userId);
        return new PageImpl<>(bookingRepository.findAllByUserId(userId, pageable)
                .stream()
                .map(bookingMapper::bookingToBookingDto)
                .collect(Collectors.toList()));
    }

    @Override
    public void deleteBooking(Long id) {
        log.info("delete booking with id {}", id);
        bookingRepository.deleteById(id);
    }
}
