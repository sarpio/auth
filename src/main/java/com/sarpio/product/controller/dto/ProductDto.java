package com.sarpio.product.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String code;
    private String name;
    private String description;
    private String brand;
    private Double price;
    private CategoryDto category;
}
