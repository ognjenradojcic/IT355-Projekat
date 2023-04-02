package com.ognjen.projekat.controller;


import com.ognjen.projekat.controller.dto.response.ApiErrorResponse;
import com.ognjen.projekat.exception.NotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiErrorResponse notFoundException(NotFoundException e) {
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public ApiErrorResponse jwtExpiredException(ExpiredJwtException e) {
        return new ApiErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
