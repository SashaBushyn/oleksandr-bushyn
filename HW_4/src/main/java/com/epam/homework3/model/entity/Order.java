package com.epam.homework3.model.entity;

import com.epam.homework3.model.enums.OrderHandling;
import com.epam.homework3.model.enums.RoomClass;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Order {
    private Long id;
    private Long userId;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private LocalDate dateCreation;
    private RoomClass roomClass;
    private Integer personsNumber;
    private OrderHandling status;
}
