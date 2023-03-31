package com.ognjen.projekat.controller.dto.request;

import com.ognjen.projekat.controller.dto.response.UserResponse;

import java.time.LocalDate;
import java.util.List;

public record InvoiceRequest(
        String address,
        LocalDate orderedAt,
        UserResponse response,
        List<InvoiceItemRequest> items
) {
    public record InvoiceItemRequest(
            ProductRequest product,
            Integer quantity
    ) {
    }

}


