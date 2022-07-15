package com.epam.homework3.repository.impl;

import com.epam.homework3.model.entity.Offer;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.OfferRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OfferRepositoryImpl implements OfferRepository {
    private final List<Offer> list = new ArrayList<>();

    @Override
    public Offer createOffer(Offer offer) {
        list.add(offer);
        return offer;
    }

    @Override
    public List<Offer> getAllOffers() {
        return new ArrayList<>(list);
    }

    @Override
    public Offer getOfferById(Long id) {
        return list.stream().filter(offer -> offer.getId().equals(id))
                .findFirst().orElseThrow(() -> new EntityException("offer with id "+ id +" is  not found"));
    }

    @Override
    public Offer updateOffer(Long id, Offer offer) {
        boolean isDeleted = list.removeIf(u -> u.getId().equals(id));
        if (isDeleted) {
            list.add(offer);
        } else {
            throw new EntityException("offer with id "+ id +" is  not found");
        }
        return offer;
    }

    @Override
    public void deleteOffer(Long id) {
        list.removeIf(order -> order.getId().equals(id));
    }

}
