package com.sarpio.product.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryDto {


    @Schema(description = "id of existing category", example = "1")
    private Long id;
    @Schema(description = "category name", example = "Category 1")
    @NotBlank(message = "Category cannot be blank")
    private String name;
}
