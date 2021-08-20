package com.sarpio.order.repository;

import com.sarpio.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT id, order_date, order_number, sent_date, status, total_value, user_id FROM " +
            "orders WHERE user_id = :userId", nativeQuery = true)
    List<OrderEntity> findAllOrders(Long userId);

    @Query(value = "SELECT * FROM orders WHERE (:id is null or id = :id) " +
            "AND (:userId is null or user_id = :userId) " +
            "AND (:status is null or status = :status)", nativeQuery = true)
    List<OrderEntity> findAdvanceOrders(Long id, Long userId, String status);

    OrderEntity getByIdAndUserId(Long id, Long userId);

    @Query(value = "SELECT * FROM orders WHERE (:id is null or user_id = :id)", nativeQuery = true)
    Optional<OrderEntity> findByUserId(Long id);
}
