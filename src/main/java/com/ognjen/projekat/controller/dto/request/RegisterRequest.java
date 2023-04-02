package com.ognjen.projekat.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterRequest(
        @NotEmpty(message = "Username cannot be empty")
        String username,
        @NotEmpty(message = "Password cannot be empty")
        String password,
        @NotEmpty(message = "First name cannot be empty")
        String firstName,
        @NotEmpty(message = "Last name cannot be empty")
        String lastName,
        @NotEmpty(message = "Phone cannot be empty")
        String phone) {
}
