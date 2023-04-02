package com.ognjen.projekat.controller.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RequestRefresh(
        @NotEmpty(message = "Refresh token field cannot be empty")
        String refreshToken
){
}
