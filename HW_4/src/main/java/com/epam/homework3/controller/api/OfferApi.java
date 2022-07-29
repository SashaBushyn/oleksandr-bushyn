package com.epam.homework3.controller.api;

import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.enums.OfferStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "offer management API")
@RequestMapping("/api/v1/")
public interface OfferApi {
    @ApiOperation("create offer")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/offer")
    OfferDto createOffer(@RequestBody OfferDto offerDto);

    @ApiOperation("get all offers")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/offer/all")
    Page<OfferDto> getAllOffer(Pageable pageable);

    @ApiOperation("change offer status")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "offer/{id}/status")
    OfferDto changeOfferStatus(@PathVariable Long id, @RequestParam("status") OfferStatus status);

    @ApiOperation("get user offers")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/offer/{id}/user")
    Page<OfferDto> getUserOffers(@PathVariable Long id, Pageable pageable);

    @ApiOperation("get offer by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/offer/{id}")
    OfferDto getOfferById(@PathVariable Long id);

    @ApiOperation("delete offer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/offer/{id}")
    void deleteOffer(@PathVariable Long id);
}
