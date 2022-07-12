package com.epam.homework3.service;

import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.enams.OfferStatus;

import java.util.List;

public interface OfferService {
    OfferDto createOffer(OfferDto orderDto);

    List<OfferDto> getAllOffers();

    OfferDto updateOffer(Long id, OfferDto offerDto);

    List<OfferDto> getUserOffers(Long userId);

    OfferDto getOfferById(Long id);

    void deleteOffer(Long id);

    OfferDto changeStatus(Long id, OfferStatus status);
}
