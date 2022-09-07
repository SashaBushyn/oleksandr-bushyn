package com.epam.homework3.controller.api;

import com.epam.homework3.controller.dto.OrderDto;
import com.epam.homework3.model.enums.OrderHandling;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "order management API")
@RequestMapping("/api/v1/")
public interface OrderApi {

    @ApiOperation("create order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order")
    OrderDto createOrder(@Valid @RequestBody OrderDto orderDto);

    @ApiOperation("get all orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order")
    Page<OrderDto> getAllOrders(Pageable pageable);

    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "path", required = true, value = "oreder id"),})
    @ApiOperation("change order status")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "order/status/{id}")
    OrderDto changeStatus(@PathVariable Long id, @RequestParam("status") OrderHandling status);

    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id"),})
    @ApiOperation("get user orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/{id}/user")
    Page<OrderDto> getUserOrders(@PathVariable Long id, Pageable pageable);

    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "path", required = true, value = "oreder id"),})
    @ApiOperation("get order by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/{id}")
    OrderDto getOrderById(@PathVariable Long id);

    @ApiImplicitParams({@ApiImplicitParam(name = "id", paramType = "path", required = true, value = "oreder id"),})
    @ApiOperation("delete order")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    void deleteOrder(@PathVariable Long id);
}
