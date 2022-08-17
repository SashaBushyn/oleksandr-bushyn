package com.epam.homework3.controller;

import com.epam.homework3.controller.api.OrderApi;
import com.epam.homework3.controller.dto.OrderDto;
import com.epam.homework3.model.enums.OrderHandling;
import com.epam.homework3.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {
    private final OrderService service;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("request to create order");
        return service.createOrder(orderDto);
    }

    @Override
    public Page<OrderDto> getAllOrders(Pageable pageable) {
        log.info("request to get all orders");
        return service.getAllOrders(pageable);
    }

    @Override
    public OrderDto changeStatus(Long id, OrderHandling status) {
        log.info("request to change status {} in order id {}", status, id);
        return service.changeStatus(id, status);
    }

    @Override
    public Page<OrderDto> getUserOrders(Pageable pageable, Long id) {
        log.info("request to get users id {} orders", id);
        return service.getUserOrders(id, pageable);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        log.info("request to get order by id {}", id);
        return service.getOrderById(id);
    }

    @Override
    public void deleteOrder(Long id) {
        log.info("request to delete order by id {}", id);
        service.deleteOrder(id);
    }
}
