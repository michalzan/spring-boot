package com.example.demo.util.model;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private String message;
    private int statusCode;
    private List<ValidationErrorEntity> errors = new ArrayList<>();

    public ValidationErrorResponse(MethodArgumentNotValidException ex) {
        message = ex.getMessage();
        statusCode = HttpStatus.BAD_REQUEST.value();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    ValidationErrorEntity entity = new ValidationErrorEntity(
                            ((FieldError) error).getField(),
                            error.getDefaultMessage(),
                            ((FieldError) error).getRejectedValue()
                    );
                    errors.add(entity);
                }
        );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<ValidationErrorEntity> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationErrorEntity> errors) {
        this.errors = errors;
    }
}
