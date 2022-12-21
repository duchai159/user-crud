package com.example.usercrud.dto;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private Integer age;
    @Email
    private String email;
}
