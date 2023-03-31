package com.ognjen.projekat.controller.dto.response;

import java.util.List;

public record ProductResponse(
        Integer id,
        String name,
        String manufacturer,
        Double price,
        CategoryResponse category,
        List<InvoiceResponse> orders
) {
}
