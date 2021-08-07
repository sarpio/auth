package com.sarpio.order.controller.dto;

import com.sarpio.order.model.OrderItemEntity;
import com.sarpio.order.model.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private String orderNumber;
    private LocalDate orderDate;
    private LocalDate sentDate;
    private StatusEnum status;
    private Double totalValue;
    private Set<OrderItemEntity> itemEntity;
    private Long userId;
}
