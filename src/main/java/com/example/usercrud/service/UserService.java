package com.example.usercrud.service;

import com.example.usercrud.dto.UserDto;
import com.example.usercrud.entity.User;
import com.example.usercrud.entity.UserLogin;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(Long id);
    void saveUser(UserDto userDto);
    void deleteUserById(Long id);
    void updateUserById(UserDto userDto);
    void getUserByName();
}
