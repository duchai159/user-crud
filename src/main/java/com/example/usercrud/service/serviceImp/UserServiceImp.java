package com.example.usercrud.service.serviceImp;

import com.example.usercrud.dto.UserDto;
import com.example.usercrud.entity.User;
import com.example.usercrud.exception.UserNotFoundException;
import com.example.usercrud.mapper.UserMapper;
import com.example.usercrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserMapper userMapper;
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userMapper.getUserById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        return userOptional.get();
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userMapper.saveUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public void updateUserById(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        Optional<User> optionalUser = userMapper.getUserById(userDto.getId());
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        userMapper.updateUserById(user);
    }
}
