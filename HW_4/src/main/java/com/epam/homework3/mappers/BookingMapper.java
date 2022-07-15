package com.epam.homework3.mappers;

import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.entity.Booking;
import com.epam.homework3.model.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingDto bookingToBookingDto(Booking booking);

    Booking bookingDtoToBooking(BookingDto bookingDto);
}
