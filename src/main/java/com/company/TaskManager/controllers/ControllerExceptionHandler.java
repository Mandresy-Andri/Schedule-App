package com.company.TaskManager.controllers;

import com.company.TaskManager.models.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    //invalid argument handler
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(IllegalArgumentException e){
        CustomErrorResponse error = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage());
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setTimestamp(LocalDateTime.now());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    //invalid request body handler
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<CustomErrorResponse>> recordValidationError(MethodArgumentNotValidException e){
        //BinidingResult holds the validation errors
        BindingResult result = e.getBindingResult();

        //Validation errors are stored in FieldError Object
        List<FieldError> fieldErrors = result.getFieldErrors();

        //translate the field errors into CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();
        for(FieldError fieldError: fieldErrors){
            CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(),fieldError.getDefaultMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            errorResponseList.add(errorResponse);
        }

        //Create and return the ResponseEntity
        ResponseEntity<List<CustomErrorResponse>> responseEntity = new ResponseEntity<>(errorResponseList,HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }
}
