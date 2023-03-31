package com.ognjen.projekat.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InvoiceItem {

    private Integer id;

    private Product product;

    private Integer quantity;
}
