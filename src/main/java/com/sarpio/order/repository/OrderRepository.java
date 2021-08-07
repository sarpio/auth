package com.sarpio.order.repository;

import com.sarpio.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT id, order_date, order_number, sent_date, status, total_value, user_id FROM orders WHERE user_id = :userId", nativeQuery = true)
    List<OrderEntity> findAllOrders(Long userId);

}
