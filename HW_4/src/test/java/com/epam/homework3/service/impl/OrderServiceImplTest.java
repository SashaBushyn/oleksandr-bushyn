package com.epam.homework3.service.impl;

import com.epam.homework3.OrderUtil;
import com.epam.homework3.controller.dto.OrderDto;
import com.epam.homework3.mappers.OrderMapper;
import com.epam.homework3.model.entity.Order;
import com.epam.homework3.model.entity.User;
import com.epam.homework3.model.enums.OrderHandling;
import com.epam.homework3.model.exception.EntityException;
import com.epam.homework3.repository.OrderRepository;
import com.epam.homework3.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Spy
    OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void createOrderUserNotFoundTest() {
        OrderDto orderDto = OrderUtil.orderDtoTest(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityException.class, () -> orderService.createOrder(orderDto));
    }

    @Test
    void createOrderTest() {
        OrderDto orderDto = OrderUtil.orderDtoTest(1);
        Order order = OrderUtil.orderTest(1);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mock(User.class)));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        orderDto = orderService.createOrder(orderDto);
        assertThat(orderDto, allOf(
                hasProperty("id", equalTo(order.getId())),
                hasProperty("dateIn", equalTo(order.getDateIn())),
                hasProperty("dateOut", equalTo(order.getDateOut())),
                hasProperty("personsNumber", equalTo(order.getPersonsNumber())),
                hasProperty("roomClass", equalTo(order.getRoomClass()))));

    }

    @Test
    void getAllOrdersTest() {
        Page<Order> orderPage = new PageImpl<>(Arrays.asList(mock(Order.class), mock(Order.class), mock(Order.class)));
        Pageable pageable = mock(Pageable.class);
        when(orderRepository.findAll(pageable)).thenReturn(orderPage);
        Page<OrderDto> orderDtos = orderService.getAllOrders(pageable);
        assertEquals(orderDtos.getTotalElements(), orderPage.getTotalElements());
    }

    @Test
    void getUserOrdersTest() {
        List<Order> orderList = (Arrays.asList(mock(Order.class), mock(Order.class), mock(Order.class)));
        Pageable pageable = mock(Pageable.class);
        when(orderRepository.findOrdersByUserId(anyLong())).thenReturn(orderList);

        Page<OrderDto> orderDtos = orderService.getUserOrders(anyLong(),pageable);

        assertEquals(orderDtos.getTotalElements(), orderList.size());
    }

    @Test
    void getOrderByIdNotFoundTest() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityException.class, () -> orderService.getOrderById(anyLong()));
    }

    @Test
    void getOrderByITest() {
        Order order = OrderUtil.orderTest(1);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        OrderDto orderDto = orderService.getOrderById(anyLong());
        assertThat(orderDto, allOf(
                hasProperty("id", equalTo(order.getId())),
                hasProperty("dateIn", equalTo(order.getDateIn())),
                hasProperty("dateOut", equalTo(order.getDateOut())),
                hasProperty("personsNumber", equalTo(order.getPersonsNumber())),
                hasProperty("roomClass", equalTo(order.getRoomClass()))));
    }

    @Test
    void deleteOrderTest() {
        orderService.deleteOrder(any());

        verify(orderRepository, times(1)).deleteById(any());
    }

    @Test
    void changeStatusTest() {
        Order persistedOrder = OrderUtil.orderTest(1);
        Order order = OrderUtil.orderTest(1);
        order.setStatus(OrderHandling.ACCEPTED);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(persistedOrder));
        when(orderRepository.save(persistedOrder)).thenReturn(order);

        OrderDto savedOrder = orderService.changeStatus(anyLong(), OrderHandling.ACCEPTED);

        assertEquals(savedOrder.getStatus(), persistedOrder.getStatus());
    }
}