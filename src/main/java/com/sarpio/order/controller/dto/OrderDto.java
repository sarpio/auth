package com.sarpio.order.controller.dto;

import com.sarpio.order.model.OrderItemEntity;
import com.sarpio.order.model.StatusEnum;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class OrderDto {

    private Long id;
    private String orderNumber;
    @Hidden
    private LocalDate orderDate;
    @Hidden
    private LocalDate sentDate;
    private StatusEnum status;
    private Double totalValue;
    private Set<OrderItemEntity> itemEntity;
    private Long userId;
}
