package com.ognjen.projekat.controller.dto.request;

import java.util.List;

public record CategoryRequest(
        String name,
        List<ProductRequest> products
) {
}
