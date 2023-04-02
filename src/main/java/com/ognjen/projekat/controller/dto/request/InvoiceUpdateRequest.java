package com.ognjen.projekat.controller.dto.request;

import java.time.LocalDate;
import java.util.List;

public record InvoiceUpdateRequest(
        Integer id,
        String address,
        LocalDate orderedAt,
        Integer userId,
        List<InvoiceItemRequest> items
) {
    public record InvoiceItemRequest(
            Integer productId,
            Integer quantity
    ) {
    }

}

