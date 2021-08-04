package com.sarpio.product.utils;

import com.sarpio.product.controller.dto.CategoryDto;
import com.sarpio.product.controller.dto.ProductDto;
import com.sarpio.product.model.CategoryEntity;
import com.sarpio.product.model.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

@RequiredArgsConstructor
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

    public static ProductDto map(ProductEntity entity) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getCategory() != null) {
            dto.setCategory(EntityDtoMapper.map(entity.getCategory()));
        }
        return dto;
    }

    public static ProductEntity map(ProductDto dto) {
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
