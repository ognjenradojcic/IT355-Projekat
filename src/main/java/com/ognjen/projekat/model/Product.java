package com.ognjen.projekat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {

    private Integer id;

    private String name;

    private String manufacturer;

    private Double price;

    private Category category;
}
