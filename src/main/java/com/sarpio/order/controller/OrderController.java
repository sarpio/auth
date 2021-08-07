package com.sarpio.order.controller;

import com.sarpio.order.controller.dto.OrderDto;
import com.sarpio.order.service.OrderService;
import com.sarpio.security.services.UserLoggedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    public final UserLoggedService logged;

    @GetMapping("/")
    public Set<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/")
    public OrderDto addOrder(@RequestBody OrderDto dto) {
        return orderService.addOrder(dto);
    }

    @GetMapping("/user")
    public Integer getLoggedUser() {
        return logged.getUserName();
    }
}
