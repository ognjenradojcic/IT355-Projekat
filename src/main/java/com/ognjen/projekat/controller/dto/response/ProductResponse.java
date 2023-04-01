package com.ognjen.projekat.controller.dto.response;

public record ProductResponse(
        Integer id,
        String name,
        String manufacturer,
        Double price,
        ProductCategoryResponse category
) {
    public record ProductCategoryResponse(
            Integer id,
            String name
    ) {

    }
}
