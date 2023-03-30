package com.ognjen.projekat.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "address")
    private String address;

    @Column(name = "ordered_date")
    private LocalDate orderedDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private UserEntity userEntity;


}
