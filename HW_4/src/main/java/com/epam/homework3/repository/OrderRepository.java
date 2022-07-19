package com.epam.homework3.repository;

import com.epam.homework3.model.entity.Order;

import java.util.List;

public interface OrderRepository {

    Order createOrder(Order order);

    List<Order> getAllOrders();

    Order updateOrder(Long id, Order order);

    Order getOrderById(Long id);

    void deleteOrder(Long id);
}
