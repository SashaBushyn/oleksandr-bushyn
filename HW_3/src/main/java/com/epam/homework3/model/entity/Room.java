package com.epam.homework3.model.entity;

import com.epam.homework3.model.enams.RoomClass;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Room {
    private Long id;
    private Integer RoomNumber;
    private RoomClass roomClass;
    private Integer personsNumber;
    private BigDecimal price;
    private boolean blocked;
}
