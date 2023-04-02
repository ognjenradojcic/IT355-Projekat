package com.ognjen.projekat.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CategoryRequest(
        @NotEmpty(message = "Name cannot be empty")
        String name,

        List<ProductRequest> products
) {
}
