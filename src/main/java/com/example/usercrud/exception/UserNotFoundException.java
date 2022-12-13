package com.example.usercrud.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserNotFoundException extends RuntimeException{
    private int result = 0;
    private String code = "404";
    private String message = "Khong tim thay";
    private Object data;
}
