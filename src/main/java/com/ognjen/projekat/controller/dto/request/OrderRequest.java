package com.ognjen.projekat.controller.dto.request;

import java.time.LocalDate;

public record OrderRequest(
        Integer quantity,
        String address,
        LocalDate orderedDate) {
}
