package com.ognjen.projekat.controller.dto.response;

import java.util.List;

public record CategoryResponse(
        Integer id,
        String name,
        List<CategoryProductResponse> products
) {
    public record CategoryProductResponse(
            Integer id,
            String name,
            String manufacturer,
            Double price){

    }
}
