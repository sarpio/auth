package com.sarpio.product.utils;

import com.sarpio.product.controller.dto.CategoryDto;
import com.sarpio.product.model.CategoryEntity;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {

    public static CategoryDto map(CategoryEntity entity) {
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static CategoryEntity map(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
