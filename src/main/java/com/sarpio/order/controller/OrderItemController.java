package com.sarpio.order.controller;

import com.sarpio.order.controller.dto.OrderItemDto;
import com.sarpio.order.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/")
    public List<OrderItemDto> allOrderItems() {
        return orderItemService.findAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItemDto findItemById(@PathVariable("id") Long id) {
        return orderItemService.findItemById(id);
    }

    @PostMapping("/")
    public OrderItemDto saveItem(@RequestBody OrderItemDto dto) {
        return orderItemService.saveItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItemById(@PathVariable("id") Long id) {
        orderItemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public void changeQuantity(@PathVariable("id") Long id, @RequestParam("qty") Integer qty) {
        orderItemService.changeQuantity(id, qty);
    }
}
