package com.ognjen.projekat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class Invoice {
    private Integer id;

    private String address;

    private LocalDate orderedAt;

    private User user;

    private List<InvoiceItem> items;
}
