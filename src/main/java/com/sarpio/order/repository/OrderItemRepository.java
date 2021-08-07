package com.sarpio.order.repository;

import com.sarpio.order.model.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    @Query(value = "SELECT sum(value) FROM order_item WHERE order_id = :id", nativeQuery = true)
    Double countOrderTotalValue(Long id);
}
