package com.ognjen.projekat.controller.dto.request;

import java.util.List;

public record CategoryUpdateRequest(
        Integer id, String name,
        List<ProductRequest> products) {
}
