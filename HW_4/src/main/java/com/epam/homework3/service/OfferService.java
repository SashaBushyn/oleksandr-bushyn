package com.epam.homework3.service;

import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.enums.OfferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfferService {
    OfferDto createOffer(OfferDto orderDto);

    Page<OfferDto> getAllOffers(Pageable pageable);

    Page<OfferDto> getUserOffers(Long userId, Pageable pageable);

    OfferDto getOfferById(Long id);

    void deleteOffer(Long id);

    OfferDto changeStatus(Long id, OfferStatus status);
}
