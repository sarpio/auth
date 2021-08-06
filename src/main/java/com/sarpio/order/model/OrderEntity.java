package com.sarpio.order.model;

import com.sarpio.product.model.ProductEntity;
import com.sarpio.security.model.UsersEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
@RequiredArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "sent_date")
    private LocalDate sentDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
//    @ColumnDefault(value = "ENTERED")
    private StatusEnum status;

    @Column(name = "total_value")
    private Double totalValue;

    @OneToMany(mappedBy = "order")
    private Set<OrderItemEntity> itemEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

}
