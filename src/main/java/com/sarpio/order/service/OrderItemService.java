package com.sarpio.order.service;

import com.sarpio.order.controller.dto.OrderItemDto;
import com.sarpio.order.model.OrderEntity;
import com.sarpio.order.model.OrderItemEntity;
import com.sarpio.order.repository.OrderItemRepository;
import com.sarpio.order.repository.OrderRepository;
import com.sarpio.order.utils.EntityDtoMapper;
import com.sarpio.product.model.ProductEntity;
import com.sarpio.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public List<OrderItemDto> findAllOrderItems() {
        return itemRepository.findAll()
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
    }

    public OrderItemDto findItemById(Long id) {
        OrderItemEntity orderItemEntity = itemRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("No item with Id: " + id));
        return EntityDtoMapper.map(orderItemEntity);
    }

    public OrderItemDto saveItem(OrderItemDto dto)  {
        ProductEntity product = productRepository.findById(dto.getProductId()).orElseThrow();
        Double price = product.getPrice();
        OrderItemEntity entity = EntityDtoMapper.map(dto);
        entity.setId(null);
        entity.setValue(price * dto.getQuantity());
        itemRepository.save(entity);
        OrderEntity order = orderRepository.findById(dto.getOrderId()).orElseThrow();
        order.setTotalValue(rounding(updateOrderValue(dto.getOrderId())));
        orderRepository.save(order);
        return EntityDtoMapper.map(entity);
    }

    public ResponseEntity deleteItem(Long id)  {
        OrderItemEntity item = itemRepository.findById(id).orElseThrow();
        Long orderId = item.getOrder().getId();
        itemRepository.deleteById(id);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        order.setTotalValue(rounding(updateOrderValue(orderId)));
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }

    public Double updateOrderValue(Long id) {
        return itemRepository.countOrderTotalValue(id);
    }

    public Double rounding(Double num) {
        double newNum = num * 100;
        double x = (double) Math.round(newNum);
        return x / 100;
    }

    public void changeQuantity(Long id, Integer qty)  {
        OrderItemEntity itemEntity = itemRepository.findById(id).orElseThrow();
        ProductEntity product = productRepository.findById(itemEntity.getProduct().getId()).orElseThrow();
        Double price = product.getPrice();
        itemEntity.setQuantity(qty);
        itemEntity.setValue(price * qty);
        itemRepository.save(itemEntity);
        OrderEntity order = orderRepository.findById(itemEntity.getProduct().getId()).orElseThrow();
        order.setTotalValue(rounding(updateOrderValue(itemEntity.getProduct().getId())));
        orderRepository.save(order);
    }
}
