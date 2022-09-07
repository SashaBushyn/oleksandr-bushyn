package com.epam.homework3.controller;

import com.epam.homework3.controller.api.OfferApi;
import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.enums.OfferStatus;
import com.epam.homework3.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OfferController implements OfferApi {
    private final OfferService service;

    @Override
    public OfferDto createOffer(OfferDto offerDto) {
        log.info("request to create offer");
        return service.createOffer(offerDto);
    }

    @Override
    public Page<OfferDto> getAllOffer(Pageable pageable) {
        log.info("request to get all offers");
        return service.getAllOffers(pageable);
    }


    @Override
    public OfferDto changeOfferStatus(Long id, OfferStatus status) {
        log.info("request to change status: {}  in offer id {}", status, id);
        return service.changeStatus(id, status);
    }

    @Override
    public Page<OfferDto> getUserOffers(Long id, Pageable pageable) {
        log.info("request to get user id {} offers", id);
        return service.getUserOffers(id, pageable);
    }

    @Override
    public OfferDto getOfferById(Long id) {
        log.info("request to offer by id {}", id);
        return service.getOfferById(id);
    }

    @Override
    public void deleteOffer(Long id) {
        log.info("request to delete offetr by id {}", id);
        service.deleteOffer(id);
    }
}
