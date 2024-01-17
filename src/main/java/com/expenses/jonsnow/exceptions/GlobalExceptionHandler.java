package com.expenses.jonsnow.exceptions;

import com.expenses.jonsnow.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchEntityException.class})
    public ResponseEntity<ResponseErrorDTO> handle(final NoSuchEntityException e){
        ResponseErrorDTO error = new ResponseErrorDTO(e.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
