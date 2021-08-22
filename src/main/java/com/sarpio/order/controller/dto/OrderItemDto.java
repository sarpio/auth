package com.sarpio.order.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.*;

@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class OrderItemDto {

    @Schema(description = "Id order item, automatically created")
    private Long id;
    @Schema(description = "order item quantity")
    @NotBlank(message = "Quantity cannot be blank")
    @NotEmpty(message = "Quantity cannot be empty")
    @NotNull(message = "Quantity cannot be null")
    @Min(1)
    private Integer quantity;
    @Schema(description = "Order item price")
    @NotBlank(message = "Price cannot be blank")
    @NotEmpty(message = "Price cannot be empty")
    @NotNull(message = "Price cannot be null")
    @Min(1)
    private Double value;
    @Schema(description = "Referenced Order id")
    @NotBlank(message = "Order Id cannot be blank")
    @NotEmpty(message = "Order Id cannot be empty")
    @NotNull(message = "Order Id cannot be null")
    private Long orderId;
    @Schema(description = "Referenced Product id")
    @NotBlank(message = "Product Id cannot be blank")
    @NotEmpty(message = "Product Id cannot be empty")
    @NotNull(message = "Product Id cannot be null")
    private Long productId;
}
