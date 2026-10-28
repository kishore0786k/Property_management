
package com.example.propertymanagement.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PropertyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleRuntimeException(RuntimeException ex) {
        return ex.getMessage();
    }

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<List<String>> handleValidationErrors(
        MethodArgumentNotValidException ex) {

    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getDefaultMessage())
        .toList();

    return ResponseEntity.badRequest().body(errors);
}
@ExceptionHandler(IllegalArgumentException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public String handleInvalidArgument(IllegalArgumentException ex) {
    return ex.getMessage();
}
}
