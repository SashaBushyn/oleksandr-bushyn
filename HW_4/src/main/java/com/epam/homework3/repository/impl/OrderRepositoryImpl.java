package com.epam.homework3.repository.impl;

import com.epam.homework3.model.entity.Order;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final List<Order> list = new ArrayList<>();

    @Override
    public Order createOrder(Order order) {
        order.setDateCreation(LocalDate.now());
        list.add(order);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(list);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        boolean isDeleted = list.removeIf(u -> u.getId().equals(id));
        if (isDeleted) {
            list.add(order);
        } else {
            throw new EntityException("order with id "+ id +" is  not found");
        }
        return order;
    }

    @Override
    public Order getOrderById(Long id) {
        return list.stream()
                .filter(order -> order.getId().equals(id)).findFirst()
                .orElseThrow(() -> new EntityException("order with id "+ id +" is  not found"));
    }

    @Override
    public void deleteOrder(Long id) {
        list.removeIf(order -> order.getId().equals(id));
    }
}
