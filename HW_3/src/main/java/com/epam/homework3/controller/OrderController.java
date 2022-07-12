package com.epam.homework3.controller;

import com.epam.homework3.controller.dto.OrderDto;
import com.epam.homework3.model.enams.OrderHandling;
import com.epam.homework3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return service.createOrder(orderDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<OrderDto> getAllOrders() {
        return service.getAllOrders();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return service.updateOrder(id, orderDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/status/{id}")
    OrderDto changeStatus(@PathVariable Long id, @RequestParam("status") OrderHandling status) {
        return service.changeStatus(id, status);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    List<OrderDto> getUserOrders(@PathVariable Long id) {
        return service.getUserOrders(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    OrderDto getOrderById(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }
}
