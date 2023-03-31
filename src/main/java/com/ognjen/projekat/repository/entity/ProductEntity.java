package com.ognjen.projekat.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    private Double price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Collection<OrderEntity> orders;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}
