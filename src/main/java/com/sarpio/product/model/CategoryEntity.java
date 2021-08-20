package com.sarpio.product.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    @NotBlank(message = "Category name cannot be blank")
    @NotEmpty(message = "Category name cannot be empty")
    @NotNull(message = "Category name cannot be null")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;
}
