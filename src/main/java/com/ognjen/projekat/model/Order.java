package com.ognjen.projekat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Order {
    private Integer id;

    private Integer quantity;

    private String address;

    private LocalDate orderedDate;
}
