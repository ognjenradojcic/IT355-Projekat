package com.ognjen.projekat.controller.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken) {
}
