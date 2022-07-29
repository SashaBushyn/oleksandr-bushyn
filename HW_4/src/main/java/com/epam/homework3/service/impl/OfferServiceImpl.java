package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.mappers.OfferMapper;
import com.epam.homework3.model.entity.*;
import com.epam.homework3.model.enums.OfferStatus;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.*;
import com.epam.homework3.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final OfferMapper offerMapper;
    private final ReservedRoomsRepository reservedRoomsRepository;

    @Override
    public OfferDto createOffer(OfferDto offerDto) {
        log.info("create offer by userId {}", offerDto.getUserId());
        Offer offer = offerMapper.offerDtoToOffer(offerDto);
        User user = userRepository.findById(offerDto.getUserId())
                .orElseThrow(() -> new EntityException("user with id: " + offerDto.getUserId() + " is not found"));
        Room room = roomRepository.findById(offerDto.getRoomId())
                .orElseThrow(() -> new EntityException("room with id: " + offerDto.getRoomId() + " is not found"));
        Order order = orderRepository.findById(offerDto.getOrderId())
                .orElseThrow(() -> new EntityException("order with id: " + offerDto.getOrderId() + " is not found"));
        offer.setUser(user);
        offer.setRoom(room);
        offer.setOrder(order);
        offer = offerRepository.save(offer);
        return offerMapper.offerToOfferDto(offer);
    }

    @Override
    public Page<OfferDto> getAllOffers(Pageable pageable) {
        log.info("get all offers");
        return new PageImpl<>(offerRepository.findAll(pageable)
                .stream()
                .map(offerMapper::offerToOfferDto)
                .collect(Collectors.toList()));
    }

    @Override
    public Page<OfferDto> getUserOffers(Long userId, Pageable pageable) {
        log.info("get userid {}  orders", userId);
        return new PageImpl<>(offerRepository.findAllByUserId(userId, pageable)
                .stream()
                .map(offerMapper::offerToOfferDto)
                .collect(Collectors.toList()));
    }

    @Override
    public OfferDto getOfferById(Long id) {
        log.info("get offer by id {}", id);
        Offer offer = offerRepository.getById(id);
        return offerMapper.offerToOfferDto(offer);
    }

    @Override
    public void deleteOffer(Long id) {
        log.info("delete offer by id {}", id);
        offerRepository.deleteById(id);
    }

    @Override
    public OfferDto changeStatus(Long id, OfferStatus status) {
        log.info("change status on {} in order id {}", status, id);
        Offer offer = offerRepository.getById(id);
        offer.setOfferStatus(status);
        offerRepository.save(offer);
        if (status == OfferStatus.ACCEPT) {
            int amountOfDays = Period.between(offer.getOrder().getDateIn(), offer.getOrder().getDateOut()).getDays();
            Booking booking = new Booking();
            booking.setDateCreation(LocalDateTime.now());
            booking.setDateIn(offer.getOrder().getDateIn());
            booking.setDateOut(offer.getOrder().getDateOut());
            booking.setRoom(offer.getRoom());
            booking.setUser(offer.getUser());
            booking.setSum(amountOfDays * offer.getRoom().getPrice());
            booking = bookingRepository.save(booking);
            for (int i = 0; i <= amountOfDays; i++) {
                ReservedRooms reservedRooms = new ReservedRooms();
                reservedRooms.setRoom(offer.getRoom());
                reservedRooms.setBooking(booking);
                reservedRooms.setDateOfReserve(booking.getDateIn().plusDays(i ));
                reservedRoomsRepository.save(reservedRooms);
            }
        }
        return offerMapper.offerToOfferDto(offer);
    }
}
