package com.epam.homework3.controller.api;

import com.epam.homework3.controller.dto.BookingDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "booking management API")
@RequestMapping("/api/v1/")
@Validated
public interface BookingApi {
    @ApiOperation("get all bookings")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/booking")
    List<BookingDto> getAllBookings();

    @ApiOperation("get user`s bookings")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "booking/{id}/user")
    List<BookingDto> getUserBookings(@PathVariable Long id);

    @ApiOperation("create booking")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/booking")
    BookingDto createBooking(@Valid @RequestBody BookingDto bookingDto);

    @ApiOperation("update booking")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/booking/{id}")
    BookingDto updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto);

    @ApiOperation("delete booking")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/booking/{id}")
    void deleteBooking(@PathVariable Long id);
}
