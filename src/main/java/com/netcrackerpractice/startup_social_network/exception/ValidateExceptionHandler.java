package com.netcrackerpractice.startup_social_network.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidateExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder errorMessages = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessages.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errorMessages.append(error.getObjectName()).append(": ").append(error.getDefaultMessage()).append("\n");
        }
        return errorMessages.toString();
    }
}
