package com.epam.homework3.service.impl;

import com.epam.homework3.controller.dto.OrderDto;
import com.epam.homework3.mappers.OrderMapper;
import com.epam.homework3.model.entity.Order;
import com.epam.homework3.model.enums.OrderHandling;
import com.epam.homework3.repository.OrderRepository;
import com.epam.homework3.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("create order by userId {}", orderDto.getUserId());
        Order order = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
        order.setId(getNewId());
        order = orderRepository.createOrder(order);
        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        log.info("get all orders");
        return orderRepository.getAllOrders()
                .stream()
                .map(OrderMapper.INSTANCE::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        log.info("update order id = {}", id);
        Order order = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
        orderRepository.updateOrder(id, order);
        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        log.info("get userid {}  orders", userId);
        return orderRepository.getAllOrders()
                .stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .map(OrderMapper.INSTANCE::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        log.info("get order by id {}", id);
        Order order = orderRepository.getOrderById(id);
        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }

    @Override
    public void deleteOrder(Long id) {
        log.info("delete order by id {}", id);
        orderRepository.deleteOrder(id);
    }

    @Override
    public OrderDto changeStatus(Long id, OrderHandling handling) {
        log.info("change status on {} in order id {}", handling, id);
        Order order = orderRepository.getOrderById(id);
        order.setStatus(handling);
        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }

    private Long getNewId() {
        Long id = orderRepository.getAllOrders()
                .stream()
                .map(Order::getId)
                .max(java.util.Comparator.naturalOrder())
                .orElse(0L);
        id++;
        return id;
    }
}
