package com.example.usercrud.mapper;

import com.example.usercrud.entity.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.*;
@Mapper
public interface AppUserMapper {
    @Select("SELECT * FROM app_user")
    List<AppUser> getAllAppUser();
    @Select("SELECT * FROM app_user WHERE USER_NAME = #{username}")
    Optional<AppUser> findUserByUserName(String username);
}