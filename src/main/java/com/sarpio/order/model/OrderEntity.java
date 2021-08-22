package com.sarpio.order.model;

import com.fasterxml.jackson.annotation.*;
import com.sarpio.security.model.UsersEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotBlank(message = "Order number cannot be blank")
    @NotEmpty(message = "Order number cannot be empty")
    @Column(name = "order_number")
    private String orderNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "order_date")
    private LocalDate orderDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "sent_date")
    private LocalDate sentDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @DecimalMin(value = "0.0")
    @Digits(integer=6, fraction=2)
    @Column(name = "total_value")
    private Double totalValue;


    @OneToMany(mappedBy = "order",
            cascade = CascadeType.REMOVE)
    private Set<OrderItemEntity> itemEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private UsersEntity user;

}
