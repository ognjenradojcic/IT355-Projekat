package com.ognjen.projekat.controller.dto.response;

public record UserResponse(
        Integer id,
        String username,
        String firstName,
        String lastName,
        String phone
) {
}
