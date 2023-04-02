package com.ognjen.projekat.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record InvoiceRequest(
        @NotEmpty(message = "Address cannot be empty")
        String address,
        LocalDate orderedAt,
        @NotNull(message = "User id cannot be null")
        Integer userId,
        List<InvoiceItemRequest> items
) {
    public record InvoiceItemRequest(
            @NotNull(message = "Product id cannot be null")
            Integer productId,
            @NotNull(message = "Quantity cannot be null")
            Integer quantity
    ) {
    }

}


