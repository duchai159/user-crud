package com.example.usercrud.mapper;

import com.example.usercrud.dto.UserDto;
import com.example.usercrud.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.*;
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user;")
    List<User> getAllUser();

    @Select("SELECT * FROM user WHERE id = #{id}")
    Optional<User> getUserById(Long id);

    @Insert("INSERT INTO user(id,name,age,email) VALUES(#{id}, #{name}, #{age},#{email})")
    void saveUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUserById(Long id);

    @Update("UPDATE user" +
            " SET name = #{name}, age = #{age}, email =#{email}" +
            " WHERE id = #{id}")
    void updateUserById(User user);
}
