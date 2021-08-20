package com.sarpio.product.model;

import com.sarpio.order.model.OrderEntity;
import com.sarpio.order.model.OrderItemEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Size(min = 5, max = 15, message
            = "About Me must be between 5 and 15 characters")
    @NotBlank(message = "code cannot be blank")
    @NotEmpty(message = "code cannot be empty")
    @Column(name = "code")
    private String code;
    @NotBlank(message = "name cannot be blank")
    @NotEmpty(message = "name cannot be empty")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "description cannot be blank")
    @NotEmpty(message = "description cannot be empty")
    @Column(name = "description")
    private String description;
    @NotBlank(message = "brand cannot be blank")
    @NotEmpty(message = "brand cannot be empty")
    @Column(name = "brand")
    private String brand;
    @NotNull(message = "price cannot be null")
    @DecimalMin(value = "0.01")
    @Digits(integer=6, fraction=2)
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private Set<OrderItemEntity> orderItem;

}
