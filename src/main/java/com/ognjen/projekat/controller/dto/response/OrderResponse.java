package com.ognjen.projekat.controller.dto.response;

import java.time.LocalDate;

public record OrderResponse(
        Integer id,
        Integer quantity,
        String address,
        LocalDate orderedDate
) {
}
