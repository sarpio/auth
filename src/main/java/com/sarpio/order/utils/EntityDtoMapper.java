package com.sarpio.order.utils;

import com.sarpio.order.controller.dto.OrderDto;
import com.sarpio.order.controller.dto.OrderItemDto;
import com.sarpio.order.model.OrderEntity;
import com.sarpio.order.model.OrderItemEntity;
import com.sarpio.product.model.ProductEntity;
import com.sarpio.security.model.UsersEntity;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {

    public static OrderDto map(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    public static OrderEntity map(OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        UsersEntity user = new UsersEntity();
        user.setId(dto.getUserId());
        BeanUtils.copyProperties(dto, entity);
        entity.setUser(user);
        return entity;
    }

    public static OrderItemDto map(OrderItemEntity entity) {
        OrderItemDto dto = new OrderItemDto();
        dto.setOrderId(entity.getOrder().getId());
        dto.setProductId(entity.getProduct().getId());
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static OrderItemEntity map(OrderItemDto dto) {
        OrderItemEntity entity = new OrderItemEntity();
        OrderEntity order = new OrderEntity();
        ProductEntity product = new ProductEntity();
        order.setId(dto.getOrderId());
        product.setId(dto.getProductId());
        BeanUtils.copyProperties(dto, entity);
        entity.setOrder(order);
        entity.setProduct(product);
        return entity;
    }
}
