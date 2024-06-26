package com.example.demo.util;

import com.example.demo.util.model.ErrorResponse;
import com.example.demo.util.model.ValidationErrorResponse;
import com.example.demo.util.model.exceptions.EntityNotFoundException;
import com.example.demo.util.model.exceptions.ServerError;
import com.example.demo.util.model.exceptions.UnprocessableContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationError(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ValidationErrorResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UnprocessableContentException.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableContent(UnprocessableContentException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(ServerError.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableContent(ServerError ex) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), ex.getMessage()),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

}
