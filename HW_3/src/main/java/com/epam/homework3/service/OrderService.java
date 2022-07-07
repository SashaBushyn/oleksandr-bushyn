package com.epam.homework3.service;

import com.epam.homework3.controller.dto.OrderDto;
import com.epam.homework3.model.enams.OrderHandling;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto updateOrder(Long id, OrderDto orderDto);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto getOrderById(Long id);

    void deleteOrder(Long id);

    OrderDto changeStatus(Long id, OrderHandling handling);

}
