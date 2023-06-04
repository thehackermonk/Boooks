package com.hacker.boooks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value={InactiveUserException.class})
    public ResponseEntity<Object> handleInactiveUserException(InactiveUserException e) {

        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;

        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                unauthorized,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, unauthorized);

    }

}
