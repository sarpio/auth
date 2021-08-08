package com.sarpio.order.controller;

import com.sarpio.order.controller.dto.OrderDto;
import com.sarpio.order.model.OrderEntity;
import com.sarpio.order.model.StatusEnum;
import com.sarpio.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/")
    public Set<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/search")
    public List<OrderDto> findOrderWithPartams(@RequestParam(value = "id", required = false) Long id,
                                               @RequestParam(value = "userId", required = false) Long userId,
                                               @RequestParam(value = "status", required = false) String status) {

        return orderService.advanceOrderSearch(id, userId, status);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/")
    public OrderDto addOrder(@RequestBody OrderDto dto) {
        return orderService.addOrder(dto);
    }

    @PostMapping("/{id}{status}")
    public OrderDto changeOrderStatus(@RequestParam("id") Long id, @RequestParam("status") StatusEnum status) {
        return orderService.changeStatus(id, status);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrderById(@PathVariable("id") Long id) {
        return orderService.deleteOrderById(id);
    }
}
