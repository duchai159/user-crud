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
    @Select("SELECT * FROM app_user WHERE username = #{username}")
    Optional<AppUser> findUserByUserName(String username);
    @Select("SELECT * FROM app_user WHERE id = #{id}")
    Optional<AppUser> findById(Long id);

    @Insert("INSERT INTO app_user(username,password) values (#{username},#{password})")
    void save(AppUser user);
}