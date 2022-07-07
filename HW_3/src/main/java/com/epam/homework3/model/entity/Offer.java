package com.epam.homework3.model.entity;

import com.epam.homework3.model.enams.OfferStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offer {
    private Long id;
    private Long userId;
    private Long roomId;
    private Long orderId;
    private OfferStatus offerStatus;
}
