package com.ognjen.projekat.controller.dto.request;

public record RegisterRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        String phone) {
}
