package com.epam.homework3.controller;

import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.enams.OfferStatus;
import com.epam.homework3.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public OfferDto createOffer(@RequestBody OfferDto offerDto) {
        return service.createOffer(offerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<OfferDto> getAllOffer() {
        return service.getAllOffers();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    OfferDto updateOffer(@PathVariable Long id, @RequestBody OfferDto offerDto) {
        return service.updateOffer(id, offerDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/status/{id}")
    OfferDto changeStatus(@PathVariable Long id, @RequestParam("status") OfferStatus status) {
        return service.changeStatus(id, status);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    List<OfferDto> getUserOffers(@PathVariable Long id) {
        return service.getUserOffers(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    OfferDto getOfferById(@PathVariable Long id) {
        return service.getOfferById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteOffer(@PathVariable Long id) {
        service.deleteOffer(id);
    }
}
