package com.ognjen.projekat.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "Username cannot be empty")
        String username,
        @NotEmpty(message = "Password cannot be empty")
        String password) {
}
