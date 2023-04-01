package com.ognjen.projekat.controller.dto.request;

public record ProductUpdateRequest(
        Integer id,
        String name,
        String manufacturer,
        Double price,
        Integer categoryId
) {
}
