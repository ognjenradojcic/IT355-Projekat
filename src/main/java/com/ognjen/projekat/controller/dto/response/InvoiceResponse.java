package com.ognjen.projekat.controller.dto.response;

import java.time.LocalDate;
import java.util.List;

public record InvoiceResponse(
        Integer id,
        String address,
        LocalDate orderedDate,
        UserResponse user,
        List<InvoiceItemResponse> items
) {

    public record InvoiceItemResponse(
            Integer id,
            ProductResponse product,
            Integer quantity
            ) {

    }
}
