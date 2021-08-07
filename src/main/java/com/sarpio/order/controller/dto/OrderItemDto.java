package com.sarpio.order.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private Long id;
    private Integer quantity;
    private Double value;
    private Long orderId;
    private Long productId;
}
