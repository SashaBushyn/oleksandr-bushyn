package com.epam.homework3.controller;

import com.epam.homework3.OrderUtil;
import com.epam.homework3.controller.dto.OrderDto;
import com.epam.homework3.model.enums.OrderHandling;
import com.epam.homework3.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @Autowired
    private ObjectMapper mapper;


    @Test
    void createOrderTest() throws Exception {
        OrderDto orderDto = OrderUtil.orderDtoTest(1);
        orderDto.setId(null);
        when(orderService.createOrder(orderDto)).thenReturn(orderDto);
        String orderStr = jsonFromObject(orderDto);

        mockMvc.perform(post("/api/v1/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderStr))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(result.getResponse().getContentAsString(), orderStr));
    }

    @Test
    void getAllOrdersTest() throws Exception {
        Page<OrderDto> orderDtoPage = new PageImpl<>(Arrays.asList(OrderUtil.orderDtoTest(1), OrderUtil.orderDtoTest(2)));
        when(orderService.getAllOrders(any(Pageable.class))).thenReturn(orderDtoPage);

        mockMvc.perform(get("/api/v1/order"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(jsonFromObject(orderDtoPage), result.getResponse().getContentAsString()));
    }

    @Test
    void changeStatusTest() throws Exception {
        OrderDto orderDto = OrderUtil.orderDtoTest(1);
        orderDto.setStatus(OrderHandling.ACCEPTED);
        when(orderService.changeStatus(orderDto.getId(), OrderHandling.ACCEPTED)).thenReturn(orderDto);
        String orderStr = jsonFromObject(orderDto);

        mockMvc.perform(put("/api/v1/order/status/" + orderDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("status", OrderHandling.ACCEPTED.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(result.getResponse().getContentAsString(), orderStr));
    }

    @Test
    void getUserOrdersTest() throws Exception {
        OrderDto orderDto = OrderUtil.orderDtoTest(1);
        Page<OrderDto> orderDtoPage = new PageImpl<>(Arrays.asList(OrderUtil.orderDtoTest(1), OrderUtil.orderDtoTest(2)));
        Pageable pageable = PageRequest.of(0, 20);
        when(orderService.getUserOrders(orderDto.getUserId(), pageable)).thenReturn(orderDtoPage);

        mockMvc.perform(get("/api/v1/order/user/" + orderDto.getUserId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(jsonFromObject(orderDtoPage), result.getResponse().getContentAsString()));

    }

    @Test
    void getOrderByIdTest() throws Exception {
        OrderDto orderDto = OrderUtil.orderDtoTest(1);
        when(orderService.getOrderById(orderDto.getId())).thenReturn(orderDto);

        mockMvc.perform(get("/api/v1/order/" + orderDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(jsonFromObject(orderDto), result.getResponse().getContentAsString()));
    }

    @Test
    void deleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(anyLong());

        mockMvc.perform(delete("/api/v1/order/" + anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @SneakyThrows
    private String jsonFromObject(Object o) {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writeValueAsString(o);
    }
}