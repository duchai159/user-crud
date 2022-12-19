package com.example.usercrud.repository;
import com.example.usercrud.entity.User;
import com.example.usercrud.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    @Autowired
    private final UserMapper userMapper;
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }
}
