package com.sarpio.product.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class CategoryDto {

    private Long id;
    private String name;
}
