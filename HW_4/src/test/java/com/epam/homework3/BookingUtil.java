package com.epam.homework3;

import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.model.entity.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingUtil {
    public static Booking testBooking(int i) {
        return Booking.builder()
                .id(Long.valueOf(i))
                .user(UserUtil.testUser(i))
                .room(RoomUtil.roomTest(i))
                .sum(Double.valueOf(100 * i))
                .dateCreation(LocalDateTime.now())
                .dateIn(LocalDate.now())
                .dateOut(LocalDate.now().plusDays(1))
                .build();
    }

    public static BookingDto testBookingDto(int i) {
        return BookingDto.builder()
                .userId((long) i)
                .roomId((long) i)
                .dateIn(LocalDate.now())
                .dateOut(LocalDate.now().plusDays(1))
                .id(Long.valueOf(i))
                .sum(Double.valueOf(100 * i))
                .userId(Long.valueOf(i))
                .build();
    }
}
