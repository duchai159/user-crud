package com.example.usercrud.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserNotFoundException extends RuntimeException{
    private int result;
    private String code;
    private String message;
    private Object data;
}
