package com.epam.homework3.model.entity;

import com.epam.homework3.model.enums.OfferStatus;
import lombok.Data;

@Data
public class Offer {
    private Long id;
    private Long userId;
    private Long roomId;
    private Long orderId;
    private OfferStatus offerStatus;
}
