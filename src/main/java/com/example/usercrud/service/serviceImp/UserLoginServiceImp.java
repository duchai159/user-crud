package com.example.usercrud.service.serviceImp;

import com.example.usercrud.entity.UserLogin;
import com.example.usercrud.repository.UserLoginRepository;
import com.example.usercrud.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImp implements UserLoginService {
    @Autowired
    UserLoginRepository userLoginRepository;
    @Override
    public void userFindByUserName(UserLogin userLogin) {
        UserLogin userLogin1 = userLoginRepository.findByUsername(userLogin.getUsername());
    }
}
