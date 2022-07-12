package com.epam.homework3.controller.dto;

import com.epam.homework3.model.enams.OrderHandling;
import com.epam.homework3.model.enams.RoomClass;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private RoomClass roomClass;
    private Integer personsNumber;
    private OrderHandling status;
}
