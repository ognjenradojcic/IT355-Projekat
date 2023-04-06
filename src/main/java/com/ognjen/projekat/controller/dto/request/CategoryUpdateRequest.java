package com.ognjen.projekat.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryUpdateRequest(
        Integer id,
        @NotEmpty(message = "Name cannot be empty")
        String name) {
}
