package com.sarpio.order.controller;

import com.sarpio.order.controller.dto.OrderDto;
import com.sarpio.order.model.StatusEnum;
import com.sarpio.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @GetMapping("filter")
    public ResponseEntity find(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "userId", required = false) Long userId,
                               @RequestParam(value = "status", required = false) String status) {

        return ResponseEntity.ok(orderService.advanceOrderSearch(id, userId, status));
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable(value = "id", required = false) Long id) {
        return ResponseEntity.ok(orderService.getOrderByUserId(id));
    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto dto) {
        orderService.addOrder(dto);
        return ResponseEntity.ok(orderService.addOrder(dto));
    }

    @PutMapping("/{id}{status}")
    public OrderDto changeOrderStatus(@RequestParam("id") Long id, @RequestParam("status") StatusEnum status)  {
        return orderService.changeStatus(id, status);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrderById(@PathVariable("id") Long id)  {
        return orderService.deleteOrderById(id);
    }
}
