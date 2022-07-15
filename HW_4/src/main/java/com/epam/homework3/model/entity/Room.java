package com.epam.homework3.model.entity;

import com.epam.homework3.model.enums.RoomClass;
import lombok.Data;

@Data
public class Room {
    private Long id;
    private Integer roomNumber;
    private RoomClass roomClass;
    private Integer personsNumber;
    private Double price;
    private boolean blocked;
}
