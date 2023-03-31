package com.ognjen.projekat.controller.dto.response;

import java.util.List;

public record CategoryResponse(
        Integer id,
        String name,
        List<ProductResponse> products
) {
}
