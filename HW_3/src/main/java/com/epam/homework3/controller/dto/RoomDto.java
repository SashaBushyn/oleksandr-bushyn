package com.epam.homework3.controller.dto;

import com.epam.homework3.model.enams.RoomClass;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
    private Long id;
    private Integer RoomNumber;
    private RoomClass roomClass;
    private Integer personsNumber;
    private boolean blocked;
}
