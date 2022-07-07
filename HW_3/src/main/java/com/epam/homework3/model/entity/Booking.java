package com.epam.homework3.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Booking {
    private Long id;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private LocalDate dateCreation;
    private Long userId;
    private Long roomId;
    private Double sum;
}
