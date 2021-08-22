package com.sarpio.product.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class ProductDto {

    @Schema(description = "id of existing product")
    private Long id;
    @Schema(description = "product code, any characters", required = true)
    @Size(min = 1, max = 255)
    private String code;
    @Schema(description = "product name", required = true)
    @Size(min = 1, max = 255)
    private String name;
    @Schema(description = "product description max 255 characters")
    @NotBlank(message = "Description cannot be blank")
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 1, max = 255)
    private String description;
    @Schema(description = "product description max 255 characters")
    @NotBlank(message = "Description cannot be blank")
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 1, max = 255)
    private String brand;
    @Schema(description = "product price")
    @NotBlank(message = "Price cannot be blank")
    @NotEmpty(message = "Price cannot be empty")
    @NotNull(message = "Price cannot be null")
    private Double price;
    @Schema(description = "Product category, provide only id of existing category")
    @NotBlank(message = "Category cannot be blank")
    @NotEmpty(message = "Category cannot be empty")
    @NotNull(message = "Category cannot be null")
    private CategoryDto category;
}
