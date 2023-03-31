package com.ognjen.projekat.controller.dto.request;

public record ProductRequest(
        String name,
        String manufacturer,
        Double price,
        CategoryRequest category
) {
}
