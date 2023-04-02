package com.ognjen.projekat.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequest(
        Integer id,
        @NotEmpty(message = "Name cannot be empty")
        String name,
        @NotEmpty(message = "Manufacturer cannot be empty")
        String manufacturer,
        @NotNull(message = "Price cannot be null")
        Double price,
        @NotNull(message = "Category id cannot be null")
        Integer categoryId
) {
}
