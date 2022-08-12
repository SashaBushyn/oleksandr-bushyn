package com.epam.homework3;

import com.epam.homework3.controller.dto.OrderDto;
import com.epam.homework3.model.entity.Order;
import com.epam.homework3.model.enums.OrderHandling;
import com.epam.homework3.model.enums.RoomClass;

import java.time.LocalDate;

public class OrderUtil {
    public static Order orderTest(int i) {
        return Order.builder()
                .id(0L + i)
                .dateIn(LocalDate.now())
                .dateOut(LocalDate.now().plusDays(i))
                .dateCreation(LocalDate.now())
                .personsNumber(i)
                .roomClass(RoomClass.ECONOM)
                .user(UserUtil.testUser(i))
                .status(OrderHandling.INPROCESS)
                .build();
    }

    public static OrderDto orderDtoTest(int i) {
        return OrderDto.builder()
                .dateIn(LocalDate.now())
                .dateOut(LocalDate.now().plusDays(i))
                .personsNumber(i)
                .roomClass(RoomClass.ECONOM)
                .userId(0L + i)
                .status(OrderHandling.INPROCESS)
                .build();
    }
}
