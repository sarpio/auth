package com.sarpio.order.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class OrderItemDto {

    private Long id;
    private Integer quantity;
    private Double value;
    private Long orderId;
    private Long productId;
}
