package com.sarpio.order.model;

import com.fasterxml.jackson.annotation.*;
import com.sarpio.security.model.UsersEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderEntity implements Serializable {

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
    private StatusEnum status;

    @Column(name = "total_value")
    private Double totalValue;


    @OneToMany(mappedBy = "order")
    private Set<OrderItemEntity> itemEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private UsersEntity user;

}
