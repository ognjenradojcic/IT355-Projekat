package com.ognjen.projekat.controller.dto.response;

public record ProductResponse(
        Integer id,
        String name,
        String manufacturer,
        Double price) {
}
