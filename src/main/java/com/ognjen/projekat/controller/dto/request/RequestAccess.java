package com.ognjen.projekat.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RequestAccess(
        @NotEmpty(message = "Access token field cannot be empty")
        String accessToken
) {
}
