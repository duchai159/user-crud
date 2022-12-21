package com.example.usercrud.mapper;

import com.example.usercrud.entity.AppUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.*;
@Mapper
public interface AppUserMapper {
    @Select("SELECT * FROM app_user")
    List<AppUser> getAllAppUser();
    @Select("SELECT * FROM app_user WHERE USER_NAME = #{username}")
    Optional<AppUser> findUserByUserName(String username);
    @Select("SELECT * FROM app_user WHERE userId = #{userId}")
    Optional<AppUser> findById(Long userId);

    @Insert("INSERT INTO app_user(username,encryptedPassword) values (#{username},#{encryptedPassword})")
    void save(AppUser user);
}