package com.example.usercrud.controller;
import com.example.usercrud.exception.ErrorMessage;
import com.example.usercrud.exception.UserNotFoundException;
import com.example.usercrud.exception.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException userNotFoundException) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .result(0)
                .code("404")
                .message("Khong tim thay nguoi dung")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .result(0)
                .code("400")
                .message("Loi request")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
