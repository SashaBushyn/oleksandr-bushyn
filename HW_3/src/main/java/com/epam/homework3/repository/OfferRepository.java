package com.epam.homework3.repository;

import com.epam.homework3.model.entity.Offer;

import java.util.List;

public interface OfferRepository {

    Offer createOffer(Offer offer);

    List<Offer> getAllOffers();

    Offer getOfferById(Long id);

    Offer updateOffer(Long id, Offer offer);

    void deleteOffer(Long id);
}
