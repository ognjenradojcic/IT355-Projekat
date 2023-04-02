package com.ognjen.projekat.controller.dto.response;

import org.springframework.http.HttpStatus;

public record ApiErrorResponse(HttpStatus status, String message) {
}
