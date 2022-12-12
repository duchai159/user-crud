package com.example.usercrud.controller;
import com.example.usercrud.exception.UserNotFoundException;
import com.example.usercrud.swaggerDto.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(userNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBindException(BindException e) {
        String errorMessage = "Lỗi request";
        if (e.hasErrors())
            e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new ResponseObject(0, "400", errorMessage, null), HttpStatus.BAD_REQUEST);
    }
}
