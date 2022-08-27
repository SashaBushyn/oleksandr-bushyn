package com.epam.homework3;

import com.epam.homework3.controller.dto.RoomDto;
import com.epam.homework3.model.entity.Room;
import com.epam.homework3.model.enums.RoomClass;

public class RoomUtil {
    public static Room roomTest(int i) {
        return Room.builder()
                .roomNumber(i)
                .id(1L +i)
                .personsNumber(i)
                .price(Double.valueOf(100 * i))
                .blocked(false)
                .roomClass(RoomClass.ECONOM)
                .build();
    }

    public static RoomDto roomDtoTest(int i) {
        return RoomDto.builder()
                .roomNumber(i)
                .id(1L +i)
                .personsNumber(i)
                .price(Double.valueOf(100 * i))
                .blocked(false)
                .roomClass(RoomClass.ECONOM)
                .build();
    }
}
