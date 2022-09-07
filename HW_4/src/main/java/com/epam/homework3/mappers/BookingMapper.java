package com.epam.homework3.mappers;

import com.epam.homework3.controller.dto.BookingDto;
import com.epam.homework3.model.entity.Booking;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mappings({@Mapping(target = "userId", source = "booking.user.id"),
            @Mapping(target = "roomId", source = "booking.room.id")})
    BookingDto bookingToBookingDto(Booking booking);

    @Mappings({@Mapping(target = "user", ignore = true),
            @Mapping(target = "room", ignore = true)})
    Booking bookingDtoToBooking(BookingDto bookingDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Booking updateOfferMapper(@MappingTarget Booking booking, BookingDto bookingDto);
}
