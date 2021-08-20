package com.sarpio.order.service;

import com.sarpio.exception.ApiRequestException;
import com.sarpio.order.controller.dto.OrderDto;
import com.sarpio.order.model.OrderEntity;
import com.sarpio.order.model.StatusEnum;
import com.sarpio.order.repository.OrderRepository;
import com.sarpio.order.utils.EntityDtoMapper;
import com.sarpio.security.services.UserLoggedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private Long userId;

    public Set<OrderDto> getAllOrders() {

//        return orderRepository.findAllOrders(UserLoggedService.getUserName().longValue())
        return orderRepository.findAll()
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toSet());
    }

    public List<OrderDto> getOrderByUserId(Long id) {
        userId = id;
        if (!UserLoggedService.isAdmin()) {
            userId = UserLoggedService.getUserName();
        }
        return orderRepository.findAll()
                .stream()
                .filter(o -> o.getUser().getId().equals(userId))
                .map(EntityDtoMapper::map)
                .collect(Collectors.toList());
    }

    public OrderDto addOrder(OrderDto dto) {
        OrderEntity entity = EntityDtoMapper.map(dto);
        entity.setOrderDate(LocalDate.now());
        orderRepository.save(entity);
        return EntityDtoMapper.map(entity);
    }

    public List<OrderDto> advanceOrderSearch(Long id, Long userId, String status) {
        List<OrderEntity> advanceOrders = orderRepository.findAdvanceOrders(id, userId, status);
        return advanceOrders.stream().map(EntityDtoMapper::map).collect(Collectors.toList());
    }

    public OrderDto changeStatus(Long id, StatusEnum status) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new ApiRequestException("No order with Id: " + id));
        orderEntity.setStatus(status);
        if (status == StatusEnum.SENT) {
            orderEntity.setSentDate(LocalDate.now());
        }
        orderRepository.save(orderEntity);
        return EntityDtoMapper.map(orderEntity);
    }

    public ResponseEntity deleteOrderById(Long id) {
        orderRepository.findById(id).orElseThrow(() -> new ApiRequestException("No Order with Id: " + id));
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
