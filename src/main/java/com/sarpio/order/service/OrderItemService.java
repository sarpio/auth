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
import net.bytebuddy.dynamic.scaffold.TypeWriter;
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

    public OrderItemDto saveItem(OrderItemDto dto) {
        ProductEntity product = productRepository.findById(dto.getProductId()).orElseThrow(()-> new NotFoundException("No product with Id: " + dto.getProductId()));
        Double price = product.getPrice();
        OrderItemEntity entity = EntityDtoMapper.map(dto);
        entity.setId(null);
        entity.setValue(price * dto.getQuantity());
        itemRepository.save(entity);
        OrderEntity order = orderRepository.findById(dto.getOrderId()).orElseThrow(()-> new NotFoundException("No order with Id: " + dto.getOrderId()));
        order.setTotalValue(rounding(updateOrderValue(dto.getOrderId())));
        orderRepository.save(order);
        return EntityDtoMapper.map(entity);
    }

    public ResponseEntity deleteItem(Long id) {
        OrderItemEntity item = itemRepository.findById(id).orElseThrow(()->new NotFoundException("No Item with Id: " +id));
        Long orderId = item.getOrder().getId();
        itemRepository.deleteById(id);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("No order with Id: " + orderId));
        order.setTotalValue(rounding(updateOrderValue(orderId)));
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }

    public Double updateOrderValue(Long id) {
        return itemRepository.countOrderTotalValue(id);
    }

    public Double rounding(Double num) {
        Double newNum = num * 100;
        Double x = Double.valueOf(Math.round(newNum));
        Double rounded = x/100;
        return rounded;
    }

    public void changeQuantity(Long id, Integer qty) {
        OrderItemEntity itemEntity = itemRepository.findById(id).orElseThrow(()->new NotFoundException("No Item with Id: " +id));
        ProductEntity product = productRepository.findById(itemEntity.getProduct().getId()).orElseThrow(()-> new NotFoundException("No product with Id: " + itemEntity.getProduct().getId()));
        Double price = product.getPrice();
        itemEntity.setQuantity(qty);
        itemEntity.setValue(price * qty);
        itemRepository.save(itemEntity);
        OrderEntity order = orderRepository.findById(itemEntity.getProduct().getId()).orElseThrow(()-> new NotFoundException("No order with Id: " + itemEntity.getProduct().getId()));
        order.setTotalValue(rounding(updateOrderValue(itemEntity.getProduct().getId())));
        orderRepository.save(order);
    }
}
