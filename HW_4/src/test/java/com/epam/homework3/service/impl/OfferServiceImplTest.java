package com.epam.homework3.service.impl;

import com.epam.homework3.OfferUtil;
import com.epam.homework3.OrderUtil;
import com.epam.homework3.RoomUtil;
import com.epam.homework3.UserUtil;
import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.mappers.OfferMapper;
import com.epam.homework3.model.entity.Booking;
import com.epam.homework3.model.entity.Offer;
import com.epam.homework3.model.entity.ReservedRooms;
import com.epam.homework3.model.enums.OfferStatus;
import com.epam.homework3.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {
    @Mock
    private OfferRepository offerRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private ReservedRoomsRepository reservedRoomsRepository;
    @Spy
    private OfferMapper offerMapper = Mappers.getMapper(OfferMapper.class);
    @InjectMocks
    private OfferServiceImpl offerService;

    @Test
    void createOfferTest() {
        Offer offer = OfferUtil.testOffer(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(UserUtil.testUser(1)));
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(RoomUtil.roomTest(1)));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderUtil.orderTest(1)));
        when(offerRepository.save(any(Offer.class))).thenReturn(offer);
        OfferDto offerDto = offerService.createOffer(OfferUtil.testOfferDto(1));

        assertThat(offerDto, allOf(
                hasProperty("id", equalTo(offer.getId())),
                hasProperty("userId", equalTo(offer.getUser().getId())),
                hasProperty("roomId", equalTo(offer.getRoom().getId())),
                hasProperty("orderId", equalTo(offer.getOrder().getId()))));
    }

    @Test
    void getAllOffersTest() {
        Page<Offer> offerPage = new PageImpl<>(Arrays.asList(mock(Offer.class), mock(Offer.class), mock(Offer.class)));
        Pageable pageable = mock(Pageable.class);
        when(offerRepository.findAll(pageable)).thenReturn(offerPage);

        Page<OfferDto> offerDtos = offerService.getAllOffers(pageable);

        assertEquals(offerDtos.getTotalElements(), offerPage.getTotalElements());
    }

    @Test
    void getUserOffersTest() {
        List<Offer> offerPage = Arrays.asList(mock(Offer.class), mock(Offer.class), mock(Offer.class));
        Pageable pageable = mock(Pageable.class);
        when(offerRepository.findAllByUserId(1L, pageable)).thenReturn(offerPage);

        Page<OfferDto> offerDtoPage = offerService.getUserOffers(1l, pageable);

        assertEquals(offerDtoPage.getTotalElements(), offerPage.size());
    }

    @Test
    void getOfferByIdTest() {
        Offer offer = OfferUtil.testOffer(1);
        when(offerRepository.getById(anyLong())).thenReturn(offer);
        OfferDto offerDto = offerService.getOfferById(anyLong());

        assertThat(offerDto, allOf(
                hasProperty("id", equalTo(offer.getId())),
                hasProperty("userId", equalTo(offer.getUser().getId())),
                hasProperty("roomId", equalTo(offer.getRoom().getId())),
                hasProperty("orderId", equalTo(offer.getOrder().getId()))));
    }

    @Test
    void deleteOffer() {
        offerService.deleteOffer(anyLong());

        verify(offerRepository, times(1)).deleteById(any());
    }

    @Test
    void changeOfferStatusOnAcceptAndCreateOfferAndTwoFieldsInReservedRoomTableTest() {
        Offer persistedOffer = OfferUtil.testOffer(1);
        Offer offer = OfferUtil.testOffer(1);
        offer.setOfferStatus(OfferStatus.ACCEPT);
        Booking booking = OfferUtil.testBookingFromOffer(1);
        when(offerRepository.getById(anyLong())).thenReturn(persistedOffer);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        OfferDto offerDto = offerService.changeStatus(anyLong(), OfferStatus.ACCEPT);
        verify(reservedRoomsRepository, times(2)).save(any(ReservedRooms.class));
    }

    @Test
    void changeOfferStatusOnDeclineTest() {
        Offer persistedOffer = OfferUtil.testOffer(1);
        Offer offer = OfferUtil.testOffer(1);
        offer.setOfferStatus(OfferStatus.DECLINE);
        when(offerRepository.getById(anyLong())).thenReturn(persistedOffer);
        when(offerRepository.save(persistedOffer)).thenReturn(offer);

        OfferDto offerDto = offerService.changeStatus(anyLong(), OfferStatus.DECLINE);

        assertEquals(offerDto.getOfferStatus(), persistedOffer.getOfferStatus());
    }
}