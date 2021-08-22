package com.sarpio.order.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sarpio.order.model.OrderItemEntity;
import com.sarpio.order.model.StatusEnum;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class OrderDto {

    @Schema(description = "Order id, automatically created")
    private Long id;
    @Schema(description = "Order number, any characters and numbers")
    @NotBlank(message = "Order number cannot be blank")
    @NotEmpty(message = "Order number cannot be empty")
    @NotNull(message = "Order number cannot be null")
    private String orderNumber;
    @Hidden
    @Schema(description = "Order date, automatic created with Order", required = true)
    @NotBlank(message = "Order date cannot be blank")
    @NotEmpty(message = "Order date cannot be empty")
    @NotNull(message = "Order date cannot be null")
    private LocalDate orderDate;
    @Hidden
    @Schema(description = "Send date, automatic when status is changed", required = true)
    @NotBlank(message = "Send date cannot be blank")
    @NotEmpty(message = "Send date cannot be empty")
    @NotNull(message = "Send date cannot be null")
    private LocalDate sentDate;
    @Schema(description = "Order Status, ENUM type, only accepted values: ENTERED, READY, PAID, SENT  ", required = true)
    @NotBlank(message = "Order Status cannot be blank")
    @NotEmpty(message = "Order Status cannot be empty")
    @NotNull(message = "Order Status cannot be null")
    private StatusEnum status;
    @Schema(description = "Total value of the orders", required = true)
    @NotBlank(message = "Total value cannot be blank")
    @NotEmpty(message = "Total value cannot be empty")
    @NotNull(message = "Total value cannot be null")
    private Double totalValue;
    @Schema(description = "List of items in Order")
    private Set<OrderItemEntity> itemEntity;
    @Schema(description = "User id who place an order")
    private Long userId;
}
