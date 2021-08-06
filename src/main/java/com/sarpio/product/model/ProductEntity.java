package com.sarpio.product.model;

import com.sarpio.order.model.OrderEntity;
import com.sarpio.order.model.OrderItemEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "brand")
    private String brand;
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private Set<OrderItemEntity> orderItem;

}
