package com.epam.homework3.mappers;

import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    OfferDto offerToOfferDto(Offer offer);

    Offer offerDtoToOffer(OfferDto offerDto);
}
