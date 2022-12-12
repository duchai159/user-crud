package com.example.usercrud.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestException extends RuntimeException{
    private int result;
    private String code;
    private String message;
    private Object data;
}
