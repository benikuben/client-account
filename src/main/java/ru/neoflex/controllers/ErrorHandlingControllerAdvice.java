package ru.neoflex.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.neoflex.dtos.ErrorResponse;
import ru.neoflex.exceptions.ClientAccountException;
import ru.neoflex.exceptions.ClientValidationException;
import ru.neoflex.exceptions.NotFoundException;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ErrorHandlingControllerAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidFormatException(InvalidFormatException e) {
        ErrorResponse response = new ErrorResponse(e.getValue() + " - invalid value");
        log.warn("InvalidFormatException {}", response);
        return response;
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> handleConstraintValidationException(ConstraintViolationException e) {
        List<ErrorResponse> response = e.getConstraintViolations().stream()
                .map(error -> new ErrorResponse(error.getMessage()))
                .toList();
        log.warn("ConstraintViolationException {}", response);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<ErrorResponse> response = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse(error.getDefaultMessage()))
                .toList();
        log.warn("MethodArgumentNotValidException {}", response);
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        log.warn("NotFoundException {}", response);
        return response;
    }

    @ExceptionHandler(ClientValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleClientValidationException(ClientValidationException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        log.warn("ClientValidationException {}", response);
        return response;
    }

    @ExceptionHandler(ClientAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleClientAccountException(ClientAccountException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        log.warn("ClientAccountException {}", response);
        return response;
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        ErrorResponse response = new ErrorResponse(e.getHeaderName() + " is not specified");
        log.warn("MissingRequestHeaderException {}", response);
        return response;
    }
}
