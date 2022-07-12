package com.epam.homework3.controller.dto;

import com.epam.homework3.model.enams.OfferStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OfferDto {
    private Long id;
    private Long userId;
    private Long roomId;
    private Long orderId;
    private OfferStatus offerStatus;
}
