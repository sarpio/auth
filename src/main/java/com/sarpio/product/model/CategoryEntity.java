package com.sarpio.product.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
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
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products;
}
