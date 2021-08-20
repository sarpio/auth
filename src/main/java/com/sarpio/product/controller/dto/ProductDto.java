package com.sarpio.product.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class ProductDto {

    private Long id;
    private String code;
    private String name;
    private String description;
    private String brand;
    private Double price;
    private CategoryDto category;
}
