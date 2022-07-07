package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.enams.OfferStatus;
import com.epam.homework3.model.entity.Booking;
import com.epam.homework3.model.entity.Offer;
import com.epam.homework3.model.entity.Order;
import com.epam.homework3.repository.BookingRepository;
import com.epam.homework3.repository.OfferRepository;
import com.epam.homework3.repository.OrderRepository;
import com.epam.homework3.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;
    private final BookingRepository bookingRepository;

    @Override
    public OfferDto createOffer(OfferDto offerDto) {
        log.info("create offer by userId {}", offerDto.getUserId());
        Offer offer = mapOfferDtoToOffer(offerDto);
        offer = offerRepository.createOffer(offer);
        return mapOfferToOfferDto(offer);
    }

    @Override
    public List<OfferDto> getAllOffers() {
        log.info("get all offers");
        return offerRepository.getAllOffers().stream().map(this::mapOfferToOfferDto).collect(Collectors.toList());
    }

    @Override
    public OfferDto updateOffer(Long id, OfferDto offerDto) {
        log.info("update order id = {}", id);
        Offer offer = mapOfferDtoToOffer(offerDto);
        offerRepository.updateOffer(id, offer);
        return mapOfferToOfferDto(offer);
    }

    @Override
    public List<OfferDto> getUserOffers(Long userId) {
        log.info("get userid {}  orders", userId);
        return offerRepository.getAllOffers().stream().filter(booking -> booking.getUserId() == userId)
                .map(this::mapOfferToOfferDto).collect(Collectors.toList());
    }

    @Override
    public OfferDto getOfferById(Long id) {
        log.info("get offer by id {}", id);
        Offer offer = offerRepository.getOfferById(id);
        return mapOfferToOfferDto(offer);
    }

    @Override
    public void deleteOffer(Long id) {
        log.info("delete offer by id {}", id);
        offerRepository.deleteOffer(id);
    }

    @Override
    public OfferDto changeStatus(Long id, OfferStatus status) {
        log.info("change status on {} in order id {}", status, id);
        Offer offer = offerRepository.getOfferById(id);
        offer.setOfferStatus(status);
        if (status == OfferStatus.ACCEPT) {
            Long orderId = offer.getOrderId();
            Order order = orderRepository.getOrderById(orderId);
            Booking booking = Booking.builder().dateCreation(LocalDate.now()).dateIn(order.getDateIn())
                    .dateOut(order.getDateOut()).roomId(offer.getRoomId()).userId(order.getUserId()).build();
            bookingRepository.createBooking(booking);
        }
        return mapOfferToOfferDto(offer);
    }

    private Offer mapOfferDtoToOffer(OfferDto offer) {
        return Offer.builder().userId(offer.getId()).offerStatus(offer.getOfferStatus()).id(offer.getId())
                .roomId(offer.getRoomId()).orderId(offer.getOrderId()).build();
    }

    private OfferDto mapOfferToOfferDto(Offer offer) {
        return OfferDto.builder().userId(offer.getId()).offerStatus(offer.getOfferStatus()).id(offer.getId())
                .roomId(offer.getRoomId()).orderId(offer.getOrderId()).build();
    }
}
