package com.sarpio.order.service;

import com.sarpio.order.controller.dto.OrderDto;
import com.sarpio.order.model.OrderEntity;
import com.sarpio.order.repository.OrderItemRepository;
import com.sarpio.order.repository.OrderRepository;
import com.sarpio.order.utils.EntityDtoMapper;
import com.sarpio.security.model.UsersEntity;
import com.sarpio.security.services.UserLoggedService;
import com.sarpio.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserLoggedService logged;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;

    public Set<OrderDto> getAllOrders() {
        return orderRepository.findAllOrders(logged.getUserName().longValue())
                .stream()
                .map(EntityDtoMapper::map)
                .collect(Collectors.toSet());
    }

    public OrderDto getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("No order with Id: " + id));
        return EntityDtoMapper.map(orderEntity);
    }

    public OrderDto addOrder(OrderDto dto) {
        OrderEntity entity = EntityDtoMapper.map(dto);
        orderRepository.save(entity);
        return EntityDtoMapper.map(entity);
    }
}
