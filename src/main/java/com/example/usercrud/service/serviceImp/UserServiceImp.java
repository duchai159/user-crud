package com.example.usercrud.service.serviceImp;

import com.example.usercrud.dto.UserDto;
import com.example.usercrud.entity.User;
import com.example.usercrud.exception.UserNotFoundException;
import com.example.usercrud.repository.UserRepository;
import com.example.usercrud.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userOptional.get();
        return user;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserById(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(user, userDto);
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        optionalUser.get().setEmail(user.getEmail());
        optionalUser.get().setAge(user.getAge());
        optionalUser.get().setName(user.getName());
        userRepository.save(optionalUser.get());
    }
}
