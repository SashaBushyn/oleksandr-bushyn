package com.epam.homework3.controller;

import com.epam.homework3.controller.api.OfferApi;
import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.enums.OfferStatus;
import com.epam.homework3.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<OfferDto> getAllOffer() {
        log.info("request to get all offers");
        return service.getAllOffers();
    }

    @Override
    public OfferDto changeOfferStatus(Long id, OfferStatus status) {
        log.info("request to change status: {}  in offer id {}", status, id);
        return service.changeStatus(id, status);
    }

    @Override
    public List<OfferDto> getUserOffers(Long id) {
        log.info("request to get user id {} offers", id);
        return service.getUserOffers(id);
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
